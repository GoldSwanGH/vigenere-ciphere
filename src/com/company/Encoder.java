package com.company;

import static com.company.Main.N;
import static com.company.Main.characters;
import static java.util.Arrays.asList;

public class Encoder {
    public static String encode(String rawInput, String rawKeyword) {
        char[] input = rawInput.replaceAll("[\\[\\]\\(\\).,!?:;-]","").toUpperCase().toCharArray();
        char[] keyword = rawKeyword.toUpperCase().toCharArray();

        StringBuilder result = new StringBuilder();

        int keyword_index = 0;

        for (char symbol : input) {
            int position = (asList(characters).indexOf(symbol) +
                    asList(characters).indexOf(keyword[keyword_index])) % N;

            result.append(characters[position]);

            if ((keyword_index + 1) == keyword.length)
                keyword_index = 0;
            else
                keyword_index++;
        }

        return result.toString();
    }

    public static String decode(String rawInput, String rawKeyword) {
        char[] input = rawInput.toUpperCase().toCharArray();
        char[] keyword = rawKeyword.toUpperCase().toCharArray();

        StringBuilder result = new StringBuilder();

        int keyword_index = 0;

        for (char symbol : input) {
            int position = (asList(characters).indexOf(symbol) + N
                    - asList(characters).indexOf(keyword[keyword_index])) % N;

            result.append(characters[position]);

            if ((keyword_index + 1) == keyword.length)
                keyword_index = 0;
            else
                keyword_index++;
        }

        return result.toString();
    }

    public static int getAlphabetsNumber(String encoded) {
        int num = -1;
        char[] chars = encoded.toUpperCase().toCharArray();



        return num;
    }
}
