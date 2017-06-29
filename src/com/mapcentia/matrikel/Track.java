package com.mapcentia.matrikel;

import java.sql.*;
import java.text.SimpleDateFormat;

/**
 * Created by mh on 6/28/17.
 */
public class Track {
    private String rel;

    Track() throws Exception {
        Configuration configuration = new Configuration();
        this.rel = configuration.getSchema() + "." + "track";
        this.createTabel();
    }

    String getLastFromDb() throws Exception {
        String ts;
        String sql = "select * from " + rel + " ORDER BY ts DESC limit 1";
        Connection c = Connect.getConnection();
        Statement stmt = c.createStatement();
        ResultSet rs = stmt.executeQuery(sql);
        rs.next();
        try {
            ts = rs.getString("ts");
        } catch (Exception e) {
            // Start date in the past
            ts = "1970-01-01 00:00:00";
            //ts = "2017-06-28 02:27:05";
        }
        stmt.close();
        return ts;
    }

    void storeInDb(String val, Integer elavskode) throws Exception {
        SimpleDateFormat sdf = new SimpleDateFormat("EEE MMM dd HH:mm:ss z yyyy");
        java.util.Date ts = sdf.parse(val);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + "(ts,elavskode) VALUES(?,?)");
        pstmt.setTimestamp(1, new java.sql.Timestamp(ts.getTime()));
        pstmt.setInt(2, elavskode);
        pstmt.executeUpdate();
        pstmt.close();
    }

    private void createTabel() throws Exception {
        String sql = "CREATE TABLE IF NOT EXISTS " + rel + " " +
                "(id         serial       PRIMARY KEY  NOT NULL, " +
                " ts         timestamp                 NOT NULL, " +
                " elavskode  int                        )";

        Connection c = Connect.getConnection();
        Statement stmt = c.createStatement();
        stmt.executeUpdate(sql);
        stmt.close();
    }
}
