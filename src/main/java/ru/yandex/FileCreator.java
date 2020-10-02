package ru.yandex;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class FileCreator {
    public static String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static void create(String pathname) {
        try {
            File file = new File(pathname);
            if (file.createNewFile()) {
                FileWriter writer = new FileWriter(file);
                StringBuilder stringBuilder = new StringBuilder();
                for (int i = 0; i < 20; i++) {
                    int a = (int) (Math.random() * ALPHABET.length());
                    stringBuilder.append(ALPHABET.charAt(a));
                }
                writer.write(stringBuilder.toString());
                writer.close();
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }


}
