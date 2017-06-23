package com.mapcentia.matrikel;

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Connection;

public class Main {

    public static void main(String[] args) throws Exception {

        if (args.length < 1) {
            System.out.println("Usage: <file.yml>");
            return;
        }

        Yaml yaml = new Yaml();
        try (InputStream in = Files.newInputStream(Paths.get(args[0]))) {
            Configuration config = yaml.loadAs(in, Configuration.class);
            System.out.println(config.toString());
        }

        Connection c = Connect.open();


        Downloader downloader = new Downloader();
        downloader.read();

        c.commit();
        c.close();
    }
}
