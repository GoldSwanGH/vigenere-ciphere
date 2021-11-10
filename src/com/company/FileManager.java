package com.company;

import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class FileManager {
    public static String getTextFromFile(String path) throws IOException {
        Path absPath = Paths.get(path);
        return Files.readAllLines(absPath).toString();
    }

    public static void putTextToFile(String path, String buffer) throws IOException {
        FileWriter writer = new FileWriter(path);
        writer.write("");
        writer.write(buffer);
        writer.close();
    }
}
