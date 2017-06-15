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
/**
 * Created by mh on 6/15/17.
 */
public class AtomReader {
    public static void read() {
        try {
            URL feedUrl = new URL("https://download.kortforsyningen.dk/sites/default/files/feeds/MATRIKELKORT_GML.xml");

            SyndFeedInput input = new SyndFeedInput();
            SyndFeed feed = input.build(new XmlReader(feedUrl));

            System.out.println("Feed Title: " + feed.getTitle());

            Date lastPublishedDate;
            Date currentDate;
            SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");


            // Get the entry items...
            for (SyndEntry entry : (List<SyndEntry>) feed.getEntries()) {

                lastPublishedDate = sdf.parse("Thu Jun 15 02:36:12 CEST 2017");

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



        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
    }
}
