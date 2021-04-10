package AnalisiLessicale;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Lexer2 {

    public static int line = 1;
    private char peek = ' ';
    
    private void readch(BufferedReader br) {
        try {
            peek = (char) br.read();
        } catch (IOException exc) {
            peek = (char) -1; // ERROR
        }
    }

    public Token lexical_scan(BufferedReader br) {
        while (peek == ' ' || peek == '\t' || peek == '\n' || peek == '\r') {
            if (peek == '\n') line++;
            readch(br);
        }
        
        switch (peek) {
            case '!':
                peek = ' ';
                return Token.not;
            case '(':
                peek = ' ';
                return Token.lpt;
            case ')':
                peek = ' ';
                return Token.rpt;
            case '{':
                peek = ' ';
                return Token.lpg;
            case '}':
                peek = ' ';
                return Token.rpg;
            case '+':
                peek = ' ';
                return Token.plus;
            case '-':
                peek = ' ';
                return Token.minus;
            case '*':
                peek = ' ';
                return Token.mult;
            case '/':
                readch(br);
                if (peek == '/') {
                    while (peek != (char) -1 && peek != '\n') {
                        readch(br);
                    }
                    return lexical_scan(br);
                } else if (peek == '*') {
                    while (peek != (char) -1) {
                        readch(br);
                        if (peek == '*') {
                            readch(br);
                            if (peek == '/') {
                                peek = ' ';
                                return lexical_scan(br);
                            }
                        }
                    }
                    System.err.println("Error: comment not closed!");
                    return null;
                } else {
                    return Token.div;
                }
            case ';':
                peek = ' ';
                return Token.semicolon;
            case '&':
                readch(br);
                if (peek == '&') {
                    peek = ' ';
                    return Word.and;
                } else {
                    System.err.println("Erroneous character"
                            + " after & : "  + peek );
                    return null;
                }
            case '|':
                readch(br);
                if (peek == '|') {
                    peek = ' ';
                    return Word.or;
                } else {
                    System.err.println("Erroneous character"
                            + " after | : "  + peek );
                    return null;
                }
            case '>':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.ge;
                } else {
                    return Word.gt;
                }
            case '<':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.le;
                } else if (peek == '>') {
                    peek = ' ';
                    return Word.ne;
                } else {
                    return Word.lt;
                }
            case '=':
                readch(br);
                if (peek == '=') {
                    peek = ' ';
                    return Word.eq;
                } else {
                    return Token.assign;
                }

            case (char)-1:
                return new Token(Tag.EOF);

            default:
                /* Identificatori */
                if (Character.isLetter(peek)) {
                    StringBuilder lexeme = new StringBuilder();
                    while (Character.isLetter(peek) || Character.isDigit(peek) || peek == '_') {
                        lexeme.append(peek);
                        readch(br);
                    }
                    switch (lexeme.toString()) {
                        case "cond": return Word.cond;
                        case "when": return Word.when;
                        case "then": return Word.then;
                        case "else": return Word.elsetok;
                        case "while": return Word.whiletok;
                        case "do": return Word.dotok;
                        case "seq": return Word.seq;
                        case "print": return Word.print;
                        case "read": return Word.read;
                        default: return new Word(Tag.ID, lexeme.toString());
                    }

                } else if (peek == '_') {
                    StringBuilder lexeme = new StringBuilder();
                    while (peek == '_') {
                        lexeme.append(peek);
                        readch(br);
                    }
                    if (Character.isLetter(peek) || Character.isDigit(peek)) {
                        while (Character.isLetter(peek) || Character.isDigit(peek) || peek == '_') {
                            lexeme.append(peek);
                            readch(br);
                        }
                        return new Word(Tag.ID, lexeme.toString());
                    } else {
                        System.err.println("Erroneous character"
                                + " after " + lexeme + " : "  + peek );
                        return null;
                    }
                    /* Costanti Numeriche */
                } else if (Character.isDigit(peek)) {
                    if (peek == '0') {
                        readch(br);
                        if (Character.isDigit(peek)) {
                            System.err.println("Error: numeric constant can't start with 0");
                            return null;
                        } else return new NumberTok(Tag.NUM, 0);
                    } else {
                        StringBuilder lexeme = new StringBuilder();
                        while (Character.isDigit(peek)) {
                            lexeme.append(peek);
                            readch(br);
                        }
                        return new NumberTok(Tag.NUM, Integer.parseInt(lexeme.toString()));
                    }
                } else {
                        System.err.println("Erroneous character: " 
                                + peek );
                        return null;
                }
         }
    }
		
    public static void main(String[] args) {
        Lexer2 lex = new Lexer2();
        String path = "./AnalisiLessicale/Test/Input.txt"; // il percorso del file da leggere
        try {
            BufferedReader br = new BufferedReader(new FileReader(path));
            Token tok;
            do {
                tok = lex.lexical_scan(br);
                System.out.println("Scan: " + tok);
            } while (tok.tag != Tag.EOF);
            br.close();
        } catch (IOException e) {e.printStackTrace();}    
    }

}
