package com.company;

import java.io.IOException;
import java.util.Scanner;

import static com.company.Main.*;
import static java.lang.System.out;

public class UI {
    private static void clearScreen() {
        out.print("\033[H\033[2J");
        out.flush();
    }

    public static void display() throws InterruptedException, IOException {
        while (true) {
            clearScreen();
            Scanner in = new Scanner(System.in);
            out.println("\nChoose one of the options below: \n1: Encode\n2: Decode\n3: Get number of used alphabets\n"
                    + "leave: Exit an application\n");

            loop: while(true) {
                switch(in.nextLine()) {
                    case "1":
                        FileManager.putTextToFile(encodedPath,
                                Encoder.encode(FileManager.getTextFromFile(plainPath), key));
                        out.println("Encoding went well, you can see results in: " + encodedPath);
                        break loop;
                    case "2":
                        FileManager.putTextToFile(plainPath,
                                Encoder.decode(FileManager.getTextFromFile(encodedPath), key));
                        out.println("Decoding went well, you can see results in: " + plainPath);
                        break loop;
                    case "3":
                        out.println("Number of alphabets being used: "
                                + Encoder.getAlphabetsNumber(FileManager.getTextFromFile(encodedPath)));
                        break loop;
                    case "leave":
                        System.exit(0);
                    default:
                        out.println("Unknown input!");
                        break;
                }
            }
            Thread.sleep(3000);
        }
    }
}
