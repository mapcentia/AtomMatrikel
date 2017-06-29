package com.mapcentia.matrikel;

import com.mapcentia.matrikel.models.*;

import javax.xml.bind.Element;
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
    private final String FREDSKOVLINIER = "fredskovLinier.sql";

    Configuration configuration = new Configuration();

    public void insertJordstykker(Deque<Jordstykke> jordstykker, Integer elavsKode) throws Exception {
        createTable(JORDSTYKKE);
        String rel = configuration.getSchema() + "." + "jordstykke";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;
        for (Jordstykke item : jordstykker) {
            int n = 0;
            System.out.print("\rIndsætter jordstykker... " + count);
            System.out.flush();
            Field[] fields = item.getClass().getDeclaredFields();
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
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;
        for (OptagetVej item : optagetVeje) {
            int n = 0;
            System.out.print("\rIndsætter optaget veje... " + count);
            System.out.flush();
            Field[] fields = item.getClass().getDeclaredFields();
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
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;
        for (Centroide item : centroider) {
            int n = 0;
            System.out.print("\rIndsætter centroider... " + count);
            System.out.flush();
            Field[] fields = item.getClass().getDeclaredFields();
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
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;
        for (Fredskov item : fredskove) {
            int n = 0;
            System.out.print("\rIndsætter fredskove... " + count);
            System.out.flush();
            Field[] fields = item.getClass().getDeclaredFields();
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
    public void insertFredskovLinier(Deque<FredskovLinie> fredskovLinier, Integer elavsKode) throws Exception {
        createTable(FREDSKOVLINIER);
        String rel = configuration.getSchema() + "." + "fredskovlinier";
        deleteElav(rel, elavsKode);
        Connection c = Connect.getConnection();
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " VALUES(?,?,?,?,?,?,?,?,?,?,?,ST_GeomFromGML(?,25832))");
        int count = 1;
        for (FredskovLinie item : fredskovLinier) {
            int n = 0;
            System.out.print("\rIndsætter fredskove... " + count);
            System.out.flush();
            Field[] fields = item.getClass().getDeclaredFields();
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
