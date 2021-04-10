package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.2 **********/

public class Identificatori {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;

        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (Character.isLetter(ch)) state = 2;
                    else if (ch == '_') state = 1;
                    else state = -1;
                    break;
                case 1:
                    if (Character.isLetter(ch) || Character.isDigit(ch)) state = 2;
                    else if (ch == '_') state = 1;
                    else state = -1;
                    break;
                case 2:
                    if (Character.isLetter(ch) || Character.isDigit(ch) || ch == '_') state = 2;
                    else state = -1;
                    break;
            }
        }
        return state == 2;
    }

    public static void main(String[] args) {
        String path,input;

        path = "./Test/Identificatori.txt";
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
