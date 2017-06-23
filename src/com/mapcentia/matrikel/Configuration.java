package com.mapcentia.matrikel;

/**
 * Created by mh on 6/22/17.
 */
import static java.lang.String.format;

public final class Configuration {

    private Connect connection;
    private Downloader kortforsyningen;
    private static String schema;


    public void setConnection(Connect connection) {
        this.connection = connection;
    }

    public void setKortforsyningen(Downloader downloader) {
        this.kortforsyningen = downloader;
    }

    public void setSchema(String schema) {
        this.schema = schema;
    }

    public String getSchema() {
        return schema;
    }

    @Override
    public String toString() {
        return new StringBuilder()
                .append(format("Connecting to database: %s\n", connection.getUrl()))
                .toString();
    }
}