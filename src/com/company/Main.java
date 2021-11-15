package com.company;

import java.io.IOException;

public class Main {
    public static Character[] characters = new Character[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K',
            'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};
    public static int N = characters.length;
    public static String key = "INFOBEZ";
    public static String plainPath = "./data/plainText.txt";
    public static String encodedPath = "./data/encodedText.txt";
    public static String decodedPath = "./data/decodedText.txt";

    //Var 5
    public static void main(String[] args) throws InterruptedException {

        try {
            UI.display();
        }
        catch (IOException e) {
            System.out.println("Files are damaged or absent!");
            System.exit(-1);
        }
        /*
        String raw = "Stop right there, criminal scum!";
        String encoded = Encoder.encode(raw, key);
        String decoded = Encoder.decode(encoded, key);
        Encoder.getAlphabetsNumber(encoded);
        System.out.println(raw);
        System.out.println(encoded);
        System.out.println(decoded);*/
    }
}
