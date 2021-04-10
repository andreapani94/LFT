package DFA;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

/********** ESERCIZIO 1.1 bis **********/

public class NoTreZeri {
    public static boolean scan(String s) {
        int state = 0;
        int i = 0;
        while (state >= 0 && i < s.length()) {
            final char ch = s.charAt(i++);
            switch (state) {
                case 0:
                    if (ch == '0') state = 1;
                    else if (ch == '1') state = 0;
                    else state = -1;
                    break;
                case 1:
                    if (ch == '0') state = 2;
                    else if (ch == '1') state = 0;
                    else state = -1;
                    break;
                case 2:
                    if (ch == '0') state = 3;
                    else if (ch == '1') state = 0;
                    else state = -1;
                    break;
                case 3:
                    if (ch == '0' ||ch == '1') state = 3;
                    else state = -1;
                    break;
            }
        }
        return state == 0 || state == 1 || state == 2;
    }

    public static void main(String[] args) {
        String path,input;

        path = "./Test/NoTreZeri.txt";
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
