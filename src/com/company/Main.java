package com.company;

import java.util.Scanner;

public class Main {

    static char[] characters = new char[] { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P',
            'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', ' '};

    static int N = characters.length;

    //зашифровать
    private static String encode(String rawInput, String rawKeyword) {
        char[] input = rawInput.toUpperCase().toCharArray();
        char[] keyword = rawKeyword.toUpperCase().toCharArray();


        StringBuilder result = new StringBuilder();

        int keyword_index = 0;

        for (char symbol : input) {
            int c = (indexOf(characters, symbol) +
                    indexOf(characters, keyword[keyword_index])) % N;

            result.append(characters[c]);

            if ((keyword_index + 1) == keyword.length)
                keyword_index = 0;
            else
                keyword_index++;
        }

        return result.toString();
    }

    //расшифровать
    private static String decode(String rawInput, String rawKeyword) {
        char[] input = rawInput.toUpperCase().toCharArray();
        char[] keyword = rawKeyword.toUpperCase().toCharArray();

        StringBuilder result = new StringBuilder();

        int keyword_index = 0;

        for (char symbol : input) {
            int p = (indexOf(characters, symbol) + N -
                    indexOf(characters, keyword[keyword_index])) % N;

            result.append(characters[p]);

            if ((keyword_index + 1) == keyword.length)
                keyword_index = 0;
            else
                keyword_index++;
        }

        return result.toString();
    }

    private static int numberOfAlphabets(String rawInput) {
        int num = -1;
        /*
        char[] chars = rawInput.toUpperCase().toCharArray();

        for (int i = 1; i < chars.length; i++) {
            ArrayList<Character>[] lists = new ArrayList[i];

        }

        ArrayList<Character> temp = new ArrayList<Character>();
        temp.add(chars[0]);

        for (int j = 0; j < N; j++) {
            int f = 0;
            for (char ch : temp) {
                
            }
        }
        */
        return num;
    }

    private static int indexOf(char[] haystack, char needle) {
        for (int i = 0; i < haystack.length; i++)
        {
            if (haystack[i] == needle) return i;
        }

        return -1;
    }

    public static void main(String[] args) {
        System.out.println("Шифр Виженера. Лебедев Владимир, лабораторная работа №3, вариант 5.");
        System.out.println("Введите 1, чтобы зашифровать текст.");
        System.out.println("Введите 2, чтобы дешифровать текст.");
        System.out.println("Введите 3, чтобы определить количество используемых алфавитов в зашифрованном тексте.");
        System.out.println("");

        boolean noInput = true;
        Scanner in = new Scanner(System.in);
        String input;

        while (noInput) {
            input = in.nextLine();
            System.out.println("");

            switch (input) {
                case "1":
                    System.out.println("Поместите файл с текстом, который требуется зашифровать, в папку с программой" +
                            "и введите его имя с расширением.");
                    break;
                case "2":
                    System.out.println("Поместите файл с текстом, который требуется дешифровать, в папку с программой" +
                            "и введите его имя с расширением.");
                    break;
                case "3":
                    System.out.println("Поместите файл с зашифрованным текстом, для которого требуется определить " +
                            "количество используемых алфавитов, в папку с программой и введите его имя с расширением.");
                    break;
                default:
                    System.out.println("Некорректный ввод. Попробуйте снова.");
                    break;
            }
            System.out.println("");
        }


        String str = "Some string to encode";
        String keyword = "java";
        String encoded = encode(str, keyword);
        String decoded = decode(encoded, keyword);
        System.out.println(str);
        System.out.println(encoded);
        System.out.println(decoded);
    }
}
