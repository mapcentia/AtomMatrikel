package com.mapcentia.matrikel;

import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

/**
 * Created by mh on 6/22/17.
 */
public class ZipFile {
    public ZipInputStream getGml(String fileName) throws Exception {

        String code = fileName.split("_")[0];



        FileInputStream fin = new FileInputStream(fileName);
        ZipInputStream zin = new ZipInputStream(fin);
        ZipEntry ze;
        while ((ze = zin.getNextEntry()) != null) {
            if (ze.getName().equals(code + "_GML_UTM32-EUREF89/MINIMAKS/" + code +".gml")) {
                //System.out.println("Gotha !");

                break;
            }
        }
        return zin;
    }
}



