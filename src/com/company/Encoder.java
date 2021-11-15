package com.company;

import java.util.ArrayList;
import java.util.HashMap;

import static com.company.Main.N;
import static com.company.Main.characters;
import static java.util.Arrays.asList;

public class Encoder {
    public static String encode(String rawInput, String rawKeyword) {
        char[] input = rawInput.replaceAll("[^a-zA-Z\\s]","").toUpperCase().toCharArray(); //
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
        float reference = 0.0667f;

        char[] chars = encoded.toUpperCase().toCharArray();
        ArrayList<Character>[] list; // храним здесь строчки, на которые разбивается входящая строка
        ArrayList<Float> indexes;
        ArrayList<Float> possibleAlphs = new ArrayList<>();

        for (int k = 1; k <= N; k++) {
            indexes = new ArrayList<>();

            // разделяем строку на подстроки
            list = new ArrayList[k];
            for (int i = 0; i < k; i++) {
                list[i] = new ArrayList<>();
            }
            for (int i = 0; i < chars.length; i++) {
                list[i % k].add(chars[i]); // записываем в нужные подстроки буквы
            }
            // считаем индексы
            for (int i = 0; i < k; i++) {
                indexes.add(getIndexOfCoincedence(list[i]));
            }

            float possibility = 0;
            for (int i = 0; i < k; i++) {
                possibility += indexes.get(i) - reference;
            }

            possibleAlphs.add(possibility);
        }

        for (int k = 0; k < N; k++) {
            if (possibleAlphs.get(k) > 0){
                return k + 1;
            }
        }

        return -1;
    }

    public static float getIndexOfCoincedence(ArrayList<Character> chars) {
        // создаем словарь и заполняем его буквами алфавита, чтобы потом считать вхождения букв в строку
        // можно вынести куда-то?
        HashMap<Character, Integer> occurs = new HashMap<>();
        for (Character character : characters) {
            occurs.put(character, 0);
        }

        // НАЧАЛО

        float result;
        int sum = 0;

        // считаем вхождения символов в строку
        for (Character aChar : chars) {
            occurs.replace(aChar, occurs.get(aChar) + 1);
        }

        // считаем сумму в числителе
        for (Character character : characters) {
            int occur = occurs.get(character);
            sum += occur * (occur - 1);
        }

        // считаем индекс
        result = (float)sum / (chars.size() * (chars.size() - 1));

        // КОНЕЦ
        return result;
    }
}
