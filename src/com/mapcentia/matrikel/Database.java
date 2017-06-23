package com.mapcentia.matrikel;

import com.mapcentia.matrikel.models.Jordstykke;

import javax.xml.bind.Element;
import java.sql.Connection;
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

    Configuration configuration = new Configuration();


    public void insertJordstykker(Deque<Jordstykke> jordstykker) throws Exception {

        String rel = configuration.getSchema() + "." + "jordstykke";

        Connection c = Connect.getConnection();
        c.setAutoCommit(false);
        PreparedStatement pstmt = c.prepareStatement("INSERT INTO " + rel + " (feat_id,matrnr,the_geom) VALUES(?,?,ST_MULTI(ST_GeomFromGML(?,25832)))");

        int n = 0;
        int count = 1;

        for (Jordstykke item : jordstykker) {

            //System.out.println(item.matrikelnummer);

            //System.out.print("\rIndsætter jordstykker... " + count);
            //System.out.flush();

            //pstmt.setString(n + 1, item.surfaceProperty); // kode
//            pstmt.setString(++n + 1, arr[n].replace("\"", "")); // navn

            Field[] fields = item.getClass().getDeclaredFields();

            for (Field f : fields) {

                System.out.print(f.getType() + " ::: " + f.getName() + " ::: " + f.get(item) + "\n");
            }

//            pstmt.executeUpdate();

            count++;

        }
        pstmt.close();


    }
}
