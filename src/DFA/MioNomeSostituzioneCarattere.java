package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.8 **********/

public class MioNomeSostituzioneCarattere {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (ch == 'A' || ch == 'a') state = 1;
                    else state = -1;
                    break;
                case 1:
                    if (ch == 'n') state = 2;
                    else state = -1;
                    break;
                case 2:
                    if (ch == 'd') state = 3;
                    else state = -1;
                    break;
                case 3:
                    if (ch == 'r') state = 4;
                    else state = -1;
                    break;
                case 4:
                    if (ch == 'e') state = 5;
                    else state = -1;
                    break;
                case 5:
                    if (ch == 'a') state = 6;
                    else state = -1;
                    break;
                case 6:
                    state = -1;
                    break;

            }
        }
        return state == 6;
    }

    public static void main(String[] args) {
        String path,input;

        path = "./Test/MioNomeSostituzioneCarattere.txt";
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
