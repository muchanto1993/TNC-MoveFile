package com.tnc.app;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.*;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App {
    public static void main(String[] args) throws IOException {
        FileReader reader = new FileReader("application.properties");

        Properties p = new Properties();
        p.load(reader);

        String keyFolder = p.getProperty("keyFolder");
        System.out.println("List Folder Parameter : " + keyFolder);

        String[] folder = keyFolder.split("\\|");
        for (int i = 0; i < folder.length; i++) {
            System.out.println("Folder Name = " + folder[i]);
        }

        String mainFolder = p.getProperty("mainFolder");
        System.out.println("Path : " + mainFolder);

        File dir = new File(mainFolder);
        String[] children = dir.list();

        if (children == null) {
            System.out.println("Either dir does not exist or is not a directory");
        } else {
            for (int i = 0; i < children.length; i++) {
                String filename = children[i];

                for (int j = 0; j < folder.length; j++) {
                    if (filename.contains(folder[j])) {
                        System.out.println(filename);

                        String fromFile = mainFolder + "/" + filename + "/File A.txt";
                        String toFile = "/Users/mab/Documents/Folder B/File A.txt";

                        Path source = Paths.get(fromFile);
                        Path target = Paths.get(toFile);
                        try {
                            // rename or move a file to other path
                            // if target exists, throws FileAlreadyExistsException
                            Files.move(source, target);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
