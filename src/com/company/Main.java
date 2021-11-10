package com.company;

import java.io.IOException;

public class Main {
    public static Character[] characters = new Character[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    public static int N = characters.length;
    public static String key = "LGBTQ";
    public static String plainPath = "./data/plainText.txt";
    public static String encodedPath = "./data/encodedText.txt";

    //Var 5
    public static void main(String[] args) throws InterruptedException {
        try {
            UI.display();
        }
        catch (IOException e) {
            System.out.println("Files are damaged or absent!");
            System.exit(-1);
        }
    }
}
