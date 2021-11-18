package com.company;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

import static com.company.Main.*;
import static java.util.Arrays.asList;

public class Encoder {
    private static class HackInfo {
        public int alphNum;
        public ArrayList<Character>[] strList;
        public HackInfo(int k, ArrayList<Character>[] list) {
            this.alphNum = k;
            this.strList = list;
        }
    }

    public static String encode(String rawInput, String rawKeyword) {
        char[] input = rawInput.replaceAll("[^a-zA-Z\\s]","").toUpperCase().toCharArray(); //
        char[] keyword = rawKeyword.replaceAll("[^a-zA-Z\\s]","").toUpperCase().toCharArray();

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

    private static HackInfo getAlphabetsNumber(String encoded) {
        float reference = 0.0667f;

        char[] chars = encoded.toUpperCase().toCharArray();
        ArrayList<Character>[] list; // храним здесь строчки, на которые разбивается входящая строка
        ArrayList<Float> indexes;

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

            if (possibility > 0) {
                return new HackInfo(k, list);
            }
        }

        return new HackInfo(-1, null);
    }

    public static int HackText(String encoded) throws IOException {
        HackInfo info = getAlphabetsNumber(encoded);
        Map<Integer, Float> currentIndexes;
        ArrayList<Integer> resultShifts = new ArrayList<>();
        Map<String, Float> textIndexes;
        String tmpKeyword;

        for (int i = 1; i < info.strList.length; i++) {
            currentIndexes = new HashMap<>();
            for (int s = 0; s < N; s++) {
                ArrayList<Character> shft = shiftString(info.strList[i], s);
                currentIndexes.put(s, getMutualCoincIdx(info.strList[0], shft));
            }
            float maxVal = findMaxIdx(new ArrayList<>(currentIndexes.values()));
            int maxShift = -1;
            for (int key : currentIndexes.keySet()) {
                if (currentIndexes.get(key) == maxVal)
                    maxShift = key;
            }

            resultShifts.add(maxShift);
        }

        tmpKeyword = Main.key;

        textIndexes = new HashMap<>();

        for (int i = 0; i < N; i++) {
            Main.key = characters[i].toString();

            for (int shift : resultShifts) {
                Main.key += characters[(i + N - shift) % N];
            }
            String tryString = decode(FileManager.getTextFromFile(encodedPath), Main.key);
            ArrayList<Character> tryText = new ArrayList<>(tryString.chars()
                .mapToObj(c -> (char) c).collect(Collectors.toList()));

            textIndexes.put(Main.key, getEnc(tryText, ' ') + getEnc(tryText, 'E'));
        }

        float maxVal = findMaxIdx(new ArrayList<>(textIndexes.values()));
        String textKey = "";

        for (String key : textIndexes.keySet()) {
            if (textIndexes.get(key) == maxVal)
                textKey = key;
        }

        System.out.println("Keyword: " + textKey);

        Main.key = tmpKeyword;

        return info.alphNum;
    }

    private static float findMaxIdx(ArrayList<Float> source) {
        float max = source.get(0);

        for (float fl : source)
            max = max > fl ? max : fl;

        return max;
    }

    private static ArrayList<Character> shiftString(ArrayList<Character> initial, int shift) {
        ArrayList<Character> shifted = new ArrayList<>();

        for (Character chr : initial) {
            shifted.add(characters[(asList(characters).indexOf(chr) + shift) % N]);
        }

        return shifted;
    }

    private static float getEnc(ArrayList<Character> chars, Character sym) {
        float result = 0;
        long enc = 0;

        for (Character chr : chars)
            enc += chr == sym ? 1 : 0;

        result = (float) enc / chars.size();

        return result;
    }

    private static float getIndexOfCoincedence(ArrayList<Character> chars) {
        // создаем словарь и заполняем его буквами алфавита, чтобы потом считать вхождения букв в строку
        HashMap<Character, Integer> occurs = new HashMap<>();
        for (Character character : characters) {
            occurs.put(character, 0);
        }

        float result;
        long sum = 0;

        // считаем вхождения символов в строку
        for (Character aChar : chars) {
            occurs.replace(aChar, occurs.get(aChar) + 1);
        }

        // считаем сумму в числителе
        for (Character character : characters) {
            long occur = occurs.get(character);
            sum += occur * (occur - 1);
        }

        // считаем индекс
        result = (float)sum / ((long)chars.size() * ((long)chars.size() - 1));

        return result;
    }

    private static float getMutualCoincIdx(ArrayList<Character> chars1, ArrayList<Character> chars2) {
        // создаем словарь и заполняем его буквами алфавита, чтобы потом считать вхождения букв в строку
        HashMap<Character, Integer> occurs1 = new HashMap<>();
        HashMap<Character, Integer> occurs2 = new HashMap<>();
        for (Character character : characters) {
            occurs1.put(character, 0);
            occurs2.put(character, 0);
        }

        float result;
        long sum = 0;

        // считаем вхождения символов в строку
        for (Character aChar : chars1) {
            occurs1.replace(aChar, occurs1.get(aChar) + 1);
        }
        for (Character aChar : chars2) {
            occurs2.replace(aChar, occurs2.get(aChar) + 1);
        }

        // считаем сумму в числителе
        for (Character character : characters) {
            long occur1 = occurs1.get(character);
            long occur2 = occurs2.get(character);
            sum += occur1 * occur2;
        }

        // считаем индекс
        result = (float) sum / ((long)chars1.size() * (long)chars2.size());

        return result;
    }
}
