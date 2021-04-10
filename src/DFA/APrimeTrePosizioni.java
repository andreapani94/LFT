package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.6 **********/

public class APrimeTrePosizioni {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (ch == 'b') state = 1;
                    else if (ch == 'a') state = 3;
                    else state = -1;
                    break;
                case 1:
                    if (ch == 'b') state = 2;
                    else if (ch == 'a') state = 3;
                    else state = -1;
                    break;
                case 2:
                    if (ch == 'a') state = 3;
                    else state = -1;
                    break;
                case 3:
                    if (ch == 'a' || ch == 'b') state = 3;
                    else state = -1;
            }
        }
        return state == 3;
    }

    public static void main(String[] args) {
        String path,input;

        path = "./Test/APrimeTrePosizioni.txt";
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
