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
import java.io.IOException;
import java.net.SocketException;

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
        FileOutputStream fos;

        ftpClient.setBufferSize(1024 * 1024);

        ZipFile zipFile = new ZipFile();

/*
        URL feedUrl = new URL("https://download.kortforsyningen.dk/sites/default/files/feeds/MATRIKELKORT_GML.xml");

        SyndFeedInput input = new SyndFeedInput();
        SyndFeed feed = input.build(new XmlReader(feedUrl));

        System.out.println("Feed Title: " + feed.getTitle());

        Date lastPublishedDate;
        Date currentDate;
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");

        String fileName;
*/
        zipFile.getGml("2006658_GML_UTM32-EUREF89.zip");
/*
        ftpClient.connect("ftp.kortforsyningen.dk");

        boolean login = ftpClient.login(user, pw);

        if (login) {
            System.out.println("Connect established...");

        } else {
            System.out.println("Connect fail...");

            throw new Exception();
        }

        for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

            lastPublishedDate = sdf.parse("Thu Jun 22 02:17:48 CEST 2017");

            currentDate = entry.getPublishedDate();

            if (currentDate.before(lastPublishedDate) || currentDate.equals(lastPublishedDate)) {
                break;

                // TODO Insert currentDate as lastPublishedDate in db

            }

            //System.out.println("Title: " + entry.getTitle());
            //System.out.println("Unique Identifier: " + entry.getUri());
            System.out.println("Published Date: " + entry.getPublishedDate());

            // Get the Links
            for (SyndLinkImpl link : (List<SyndLinkImpl>) entry.getLinks()) {
                System.out.println("Link: " + link.getHref());

                fileName = link.getHref().split("/")[7];

                ftpClient.setFileType(FTP.BINARY_FILE_TYPE);

                fos = new FileOutputStream(fileName);
                boolean download = ftpClient.retrieveFile("atomfeeds/MATRIKELKORT/ATOM/GML/" + fileName, fos);
                if (download) {
                    System.out.println("File downloaded successfully !");

                    zipFile.getGml(fileName);

                } else {
                    System.out.println("Error in downloading file !");
                }
            }

            // Get the Contents
            for (SyndContentImpl content : (List<SyndContentImpl>) entry.getContents()) {
                // System.out.println("Content: " + content.getValue());
            }

            // Get the Categories
            for (SyndCategoryImpl category : (List<SyndCategoryImpl>) entry.getCategories()) {
                // System.out.println("Category: " + category.getName());
            }


        }

        // logout the u  ser, returned true if logout successfully
        boolean logout = ftpClient.logout();

        if (logout) {
            System.out.println("FTP close...");
        }

*/
    }

}
