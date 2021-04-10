package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.4 **********/

public class NumeriMatricolaT2T3Spazi {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (Character.isWhitespace(ch)) state = 0;
                    else if (Character.isDigit(ch) && ((int) ch % 2 == 0)) state = 1;
                    else if (Character.isDigit(ch) && ((int) ch % 2 != 0)) state = 2;
                    else state = -1;
                    break;
                case 1:
                    if (Character.isWhitespace(ch)) state = 3;
                    else if (Character.isUpperCase(ch) && ch >= 'A' && ch <= 'K') state = 4;
                    else if (Character.isDigit(ch) && ch % 2 == 0) state = 1;
                    else if (Character.isDigit(ch) && ch % 2 != 0) state = 2;
                    else state = -1;
                    break;
                case 2:
                    if (Character.isWhitespace(ch)) state = 6;
                    else if (Character.isDigit(ch) && ch % 2 == 0) state = 1;
                    else if (Character.isDigit(ch) && ch % 2 != 0) state = 2;
                    else state = -1;
                    break;
                case 3:
                    if (Character.isWhitespace(ch)) state = 3;
                    else if (Character.isUpperCase(ch) && ch >= 'A' && ch <= 'K') state = 4;
                    else state = -1;
                    break;
                case 4:
                    if (Character.getType(ch) == Character.LOWERCASE_LETTER) state = 4;
                    else if (Character.isWhitespace(ch)) state = 5;
                    else state = -1;
                    break;
                case 5:
                    if (Character.isWhitespace(ch)) state = 5;
                    else if (Character.isUpperCase(ch)) state = 4;
                    else state = -1;
                    break;
                case 6:
                    if (Character.isWhitespace(ch)) state = 6;
                    else if (Character.isUpperCase(ch) && ch >= 'L' && ch <= 'Z') state = 4;
                    else state = -1;
                    break;

            }
        }
        return state == 4 || state == 5;
    }
    public static void main(String[] args) {
        String path,input;

        path = "./Test/NumeriMatricolaT2T3Spazi.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((input = br.readLine()) != null) {
                input = input.substring(1, input.length()-1);
                System.out.println(input + ": " + (scan(input) ? "OK" : "NOPE"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
