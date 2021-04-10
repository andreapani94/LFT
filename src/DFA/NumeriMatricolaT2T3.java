package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.3 **********/

public class NumeriMatricolaT2T3 {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (Character.isDigit(ch) && ((int) ch % 2 == 0)) state = 1;
                    else if (Character.isDigit(ch) && ((int) ch % 2 != 0)) state = 2;
                    else state = -1;
                    break;
                case 1: //numeri pari
                    if (Character.isLetter(ch) && ch >= 'A' && ch <= 'K') state = 3;
                    else if (Character.isDigit(ch) && ch % 2 == 0) state = 1;
                    else if (Character.isDigit(ch) && ch % 2 != 0) state = 2;
                    else state = -1;
                    break;
                case 2: //numeri dispari
                    if (Character.isLetter(ch) && ch >= 'L' && ch <= 'Z') state = 3;
                    else if (Character.isDigit(ch) && ch % 2 == 0) state = 1;
                    else if (Character.isDigit(ch) && ch % 2 != 0) state = 2;
                    else state = -1;
                    break;
                case 3:
                    if (Character.getType(ch) == Character.LOWERCASE_LETTER) state = 3;
                    else state = -1;
                    break;
            }
        }
        return state == 3;
    }
    public static void main(String[] args) {
        String path,input;

        path = "./Test/NumeriMatricolaT2T3.txt";
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            while ((input = br.readLine()) != null) {
                System.out.println(input + ": " + (scan(input) ? "OK" : "NOPE"));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
