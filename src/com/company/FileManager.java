package com.company;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

public class FileManager {
    /*public static String getTextFromFile(String path) throws IOException {
        Path absPath = Paths.get(path);
        return Files.readAllLines(absPath, StandardCharsets.UTF_8).toString();
    }*/

    public static String getTextFromFile(String path) throws IOException {
        File file = new File(path);
        Scanner reader = new Scanner(file);
        StringBuilder str = new StringBuilder();

        while (reader.hasNextLine()) {
            str.append(reader.nextLine());
        }

        return str.toString();
    }

    public static void putTextToFile(String path, String buffer) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write("");
        writer.write(buffer);
        writer.close();
    }
}
