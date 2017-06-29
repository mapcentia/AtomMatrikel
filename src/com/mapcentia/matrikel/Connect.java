package com.mapcentia.matrikel;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * Created by mh on 26/10/16.
 */
public final class Connect {

    private static String url;
    private static String user;
    private static String pw;
    public static Connection c;

    static String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public void setPw(String pw) {
        this.pw = pw;
    }

    static Connection getConnection() {
        return c;
    }

    static Connection open() throws Exception {
        c = DriverManager.getConnection(url, user, pw);
        c.setAutoCommit(false);
        return c;
    }
}
