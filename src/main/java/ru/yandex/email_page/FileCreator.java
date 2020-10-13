package ru.yandex.email_page;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

public class FileCreator {
    private static final String ALPHABET = "abcdefghijklmnopqrstuvwxyz0123456789";

    public static File create() {

        try {
            File file = new File(UUID.randomUUID().toString() + ".txt");
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
            return file;
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
        return null;
    }


}
