package com.mapcentia.matrikel;

import com.mapcentia.matrikel.models.*;
import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.*;
import java.lang.reflect.Field;

/**
 * Created by mh on 6/22/17.
 */
public class Database {

    private final String JORDSTYKKE = "jordstykke.sql";
    private final String OPTAGETVEJ = "optagetVej.sql";
    private final String CENTROIDE = "centroide.sql";
    private final String FREDSKOV = "fredskov.sql";
    private final String STRANDBESKYTTELSE = "strandbeskyttelse.sql";

    Configuration configuration = new Configuration();

    public void insertJordstykker(Deque<Jordstykke> jordstykker, Integer elavsKode) throws Exception {
        createTable(JORDSTYKKE);
        String rel = configuration.getSchema() + "." + "jordstykke";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(default,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;

        Field[] fields = Jordstykke.class.getDeclaredFields();

        for (Jordstykke item : jordstykker) {
            int n = 0;
            System.out.print("\rIndsætter jordstykker... " + count);
            System.out.flush();
            for (Field f : fields) {
                if (f.getType() == (Class.forName("java.lang.String"))) {
                    pstmt.setString(++n, (String) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Integer"))) {
                    pstmt.setInt(++n, (Integer) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Double"))) {
                    pstmt.setDouble(++n, (Double) f.get(item));
                } else if (f.getType() == (Class.forName("java.sql.Timestamp"))) {
                    pstmt.setTimestamp(++n, (Timestamp) f.get(item));
                } else {
                    pstmt.setString(++n, (String) f.get(item));
                }
            }
            pstmt.executeUpdate();
            count++;
        }
        System.out.print("\n");
        pstmt.close();
    }

    public void insertOptagetVej(Deque<OptagetVej> optagetVeje, Integer elavsKode) throws Exception {
        createTable(OPTAGETVEJ);
        String rel = configuration.getSchema() + "." + "optagetvej";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(default,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;

        Field[] fields = OptagetVej.class.getDeclaredFields();

        for (OptagetVej item : optagetVeje) {
            int n = 0;
            System.out.print("\rIndsætter optaget veje... " + count);
            System.out.flush();
            for (Field f : fields) {
                if (f.getType() == (Class.forName("java.lang.String"))) {
                    pstmt.setString(++n, (String) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Integer"))) {
                    pstmt.setInt(++n, (Integer) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Double"))) {
                    pstmt.setDouble(++n, (Double) f.get(item));
                } else if (f.getType() == (Class.forName("java.sql.Timestamp"))) {
                    pstmt.setTimestamp(++n, (Timestamp) f.get(item));
                } else {
                    pstmt.setString(++n, (String) f.get(item));
                }
            }
            pstmt.executeUpdate();
            count++;
        }
        System.out.print("\n");
        pstmt.close();
    }

    public void insertCentroide(Deque<Centroide> centroider, Integer elavsKode) throws Exception {
        createTable(CENTROIDE);
        String rel = configuration.getSchema() + "." + "centroide";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(default,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;

        Field[] fields = Centroide.class.getDeclaredFields();

        for (Centroide item : centroider) {
            int n = 0;
            System.out.print("\rIndsætter centroider... " + count);
            System.out.flush();
            for (Field f : fields) {
                if (f.getType() == (Class.forName("java.lang.String"))) {
                    pstmt.setString(++n, (String) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Integer"))) {
                    pstmt.setInt(++n, (Integer) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Double"))) {
                    pstmt.setDouble(++n, (Double) f.get(item));
                } else if (f.getType() == (Class.forName("java.sql.Timestamp"))) {
                    pstmt.setTimestamp(++n, (Timestamp) f.get(item));
                } else {
                    pstmt.setString(++n, (String) f.get(item));
                }
            }
            pstmt.executeUpdate();
            count++;
        }
        System.out.print("\n");
        pstmt.close();
    }
    public void insertFredskov(Deque<Fredskov> fredskove, Integer elavsKode) throws Exception {
        createTable(FREDSKOV);
        String rel = configuration.getSchema() + "." + "fredskov";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(default,?,?,?,?,?,?,?,?,?,?,?,ST_MULTI(ST_GeomFromGML(?,25832)))");
        int count = 1;

        Field[] fields = Fredskov.class.getDeclaredFields();

        for (Fredskov item : fredskove) {
            int n = 0;
            System.out.print("\rIndsætter fredskove... " + count);
            System.out.flush();
            for (Field f : fields) {
                if (f.getType() == (Class.forName("java.lang.String"))) {
                    pstmt.setString(++n, (String) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Integer"))) {
                    pstmt.setInt(++n, (Integer) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Double"))) {
                    pstmt.setDouble(++n, (Double) f.get(item));
                } else if (f.getType() == (Class.forName("java.sql.Timestamp"))) {
                    pstmt.setTimestamp(++n, (Timestamp) f.get(item));
                } else {
                    pstmt.setString(++n, (String) f.get(item));
                }
            }
            pstmt.executeUpdate();
            count++;
        }
        System.out.print("\n");
        pstmt.close();
    }
    public void insertStrandbeskyttelse(Deque<Strandbeskyttelse> strandbeskyttelser, Integer elavsKode) throws Exception {
        createTable(STRANDBESKYTTELSE);
        String rel = configuration.getSchema() + "." + "strandbeskyttelse";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(default,?,?,?,?,?,?,?,?,?,?,?,ST_MULTI(ST_GeomFromGML(?,25832)))");
        int count = 1;

        Field[] fields = Strandbeskyttelse.class.getDeclaredFields();

        for (Strandbeskyttelse item : strandbeskyttelser) {
            int n = 0;
            System.out.print("\rIndsætter strandbeskyttelser... " + count);
            System.out.flush();
            for (Field f : fields) {
                if (f.getType() == (Class.forName("java.lang.String"))) {
                    pstmt.setString(++n, (String) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Integer"))) {
                    pstmt.setInt(++n, (Integer) f.get(item));
                } else if (f.getType() == (Class.forName("java.lang.Double"))) {
                    pstmt.setDouble(++n, (Double) f.get(item));
                } else if (f.getType() == (Class.forName("java.sql.Timestamp"))) {
                    pstmt.setTimestamp(++n, (Timestamp) f.get(item));
                } else {
                    pstmt.setString(++n, (String) f.get(item));
                }
            }
            pstmt.executeUpdate();
            count++;
        }
        System.out.print("\n");
        pstmt.close();

    }

    public void createTable(String name) throws Exception {
            String sqlCreate = String.format(readSql("ressources/" + name), configuration.getSchema());
            Connection c = Connect.getConnection();
            Statement stmt = c.createStatement();
            stmt.execute(sqlCreate);
    }

    public void deleteElav(String rel, Integer elavsKode) throws Exception{
        //System.out.print("Sletter rows for ejerlav " + elavsKode.toString() + "\n");
        Connection c = Connect.getConnection();
        PreparedStatement pstmtDelete = c.prepareStatement("DELETE FROM " + rel + " WHERE landsejerlavskode=?");
        pstmtDelete.setInt(1, elavsKode); // kode
        pstmtDelete.executeUpdate();
        pstmtDelete.close();
    }

    private String readSql(String name) throws Exception {
        String query;
        BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(getClass().getResourceAsStream(name))
        );

        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = bufferedReader.readLine()) != null) {
            sb.append(line);
        }
        query = sb.toString();
        return query;
    }
}
