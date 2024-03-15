package coloring;

import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.util.HashMap;
import java.util.List;

public class Coloring {
    List<Token> tokens;
    HashMap<TokenType, String> coloringMap = new HashMap<>();
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_GRAY = "\u001b[38;2;38;70;83m";
    public static final String ANSI_RED_BACKGROUND = "\u001b[48;2;214;40;40m";
    public static final String ANSI_ORANGE = "\u001b[38;2;231;111;81m";
    public static final String ANSI_LIGHT_ORANGE = "\u001b[38;2;244;162;97m";
    public static final String ANSI_YELLOW = "\u001b[38;2;233;196;106m";
    public static final String ANSI_BLUE = "\u001b[38;2;42;157;143m";
    public static final String ANSI_SKY_BLUE = "\u001b[38;2;0;180;216m";
    public static final String ANSI_WHITE = "\u001B[15m";

    public Coloring(String inputFile) throws Exception {
        Scanner scanner = new Scanner(inputFile);
        tokens = scanner.getAllTokens();
        this.makeColoringMap();
    }

    private void makeColoringMap() {
        coloringMap.put(TokenType.RESERVED, ANSI_LIGHT_ORANGE);
        coloringMap.put(TokenType.BUILT_IN_TYPES, ANSI_BLUE);
        coloringMap.put(TokenType.SYMBOL, ANSI_WHITE);
        coloringMap.put(TokenType.STRING, ANSI_YELLOW);
        coloringMap.put(TokenType.NUMBER, ANSI_SKY_BLUE);
        coloringMap.put(TokenType.QUOTATION, ANSI_ORANGE);
        coloringMap.put(TokenType.COMMENT, ANSI_GRAY);
        coloringMap.put(TokenType.WHITE_SPACE, "");
        coloringMap.put(TokenType.EOF, "");
        coloringMap.put(TokenType.ERROR, ANSI_RED_BACKGROUND);

    }

    public void print() {
        for(Token t : tokens)
            System.out.print(coloringMap.get(t.getType()) + t.getToken() + ANSI_RESET);
    }
}
