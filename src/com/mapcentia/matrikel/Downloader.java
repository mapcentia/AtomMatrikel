package com.mapcentia.matrikel;

import com.sun.syndication.feed.synd.SyndCategoryImpl;
import com.sun.syndication.feed.synd.SyndContentImpl;
import com.sun.syndication.feed.synd.SyndEntry;
import com.sun.syndication.feed.synd.SyndFeed;
import com.sun.syndication.feed.synd.SyndLinkImpl;
import com.sun.syndication.io.SyndFeedInput;
import com.sun.syndication.io.XmlReader;

import java.text.SimpleDateFormat;
import java.net.URL;
import java.util.Date;
import java.util.List;

import java.io.FileOutputStream;
import java.io.File;

import java.util.zip.ZipInputStream;

import org.apache.commons.net.ftp.*;

/**
 * Created by mh on 6/15/17.
 */
public final class Downloader {

    private static String user;
    private static String pw;

    public void setUser(String user) {
        this.user = user;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    public void read() throws Exception {
        FTPClient ftpClient = new FTPClient();
        ftpClient.setBufferSize(1024 * 1024);
        ftpClient.setDefaultTimeout(1000);
        ftpClient.setControlEncoding("UTF-8");
        ftpClient.setDataTimeout(10000);
        ZipFile zipFile = new ZipFile();
        Parser parser = new Parser();
        URL feedUrl = new URL("https://download.kortforsyningen.dk/sites/default/files/feeds/MATRIKELKORT_GML.xml");
        SyndFeedInput input = new SyndFeedInput();
        System.out.println("Henter Atom feed....\n");

        SyndFeed feed = input.build(new XmlReader(feedUrl));
        System.out.println("Feed Titel: " + feed.getTitle());
        Date lastPublishedDate;
        Date currentDate;
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String fileName;
        Integer elavsKode;

        Track track = new Track();
        String lastStr = track.getLastFromDb();
        lastPublishedDate = sdf.parse(lastStr);

        System.out.println("\nSidste entry: " + lastStr + "\n");

        this.login(ftpClient);

        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

            System.out.println("\n--------------\n");

            currentDate = entry.getPublishedDate();

            if (currentDate.before(lastPublishedDate) || currentDate.equals(lastPublishedDate)) {
                break;
            }


            System.out.println("Published Date: " + entry.getPublishedDate() + "\n");

            // Get the Links
            for (SyndLinkImpl link : (List<SyndLinkImpl>) entry.getLinks()) {
                System.out.println("Link: " + link.getHref());
                fileName = link.getHref().split("/")[7];
                elavsKode = Integer.valueOf(fileName.split("_")[0]);
                track.storeInDb(currentDate.toString(), elavsKode);

                // Get the file
                this.c(ftpClient, fileName, zipFile);

                // Get xml stream from zip-file
                ZipInputStream zin = zipFile.getGml(fileName);

                // Parse the xml and insert in database
                parser.build(zin, elavsKode);

                // Delete the file
                File file = new File(fileName);
                file.delete();
            }


        }
        // logout the user, returned true if logout successfully
        ftpClient.disconnect();
        System.out.println("Alt hentet...");
        System.out.println("FTP forbindelse afbrydes.");


    }

    public void c(FTPClient ftpClient, String fileName, ZipFile zipFile) throws Exception {
        FileOutputStream fos;
        try {
            ftpClient.setFileType(FTP.BINARY_FILE_TYPE);
            fos = new FileOutputStream(fileName);
            boolean download = ftpClient.retrieveFile("atomfeeds/MATRIKELKORT/ATOM/GML/" + fileName, fos);
            if (download) {
                System.out.println("Fil downloaded !");
            } else {
                System.out.println("Fejl i downloading af fil !");
                Thread.sleep(1000);
                System.out.println("Reconnecting....\n");
                ftpClient.disconnect();
                this.login(ftpClient);
                // Recursive call
                this.c(ftpClient, fileName, zipFile);
            }
        } catch (Exception e) {
            System.out.println("Socket timeout....\n");
            Thread.sleep(1000);
            System.out.println("Reconnecting....\n");
            ftpClient.disconnect();
            this.login(ftpClient);
            // Recursive call
            this.c(ftpClient, fileName, zipFile);
        }
    }

    public void login(FTPClient ftpClient) throws Exception {
        boolean login = false;
        try {
            ftpClient.connect("ftp.kortforsyningen.dk");
            login = ftpClient.login(user, pw);

        } catch (Exception e) {
            System.out.println("Connect error....\n");
            Thread.sleep(1000);
            System.out.println("Reconnecting....\n");
            // Recursive call
            this.login(ftpClient);
        }
        if (login) {
            System.out.println("Forbindelse etableret...");
            ftpClient.setSoTimeout(500);
        } else {
            System.out.println("Login fejlede...");
            ftpClient.disconnect();
            // Recursive call
            this.login(ftpClient);

        }
    }

}