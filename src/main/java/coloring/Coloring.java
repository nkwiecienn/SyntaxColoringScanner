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
    public static final String ANSI_GREY = "\u001B[90m";
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";
    public static final String ANSI_WHITE = "\u001B[37m";

    public Coloring(String inputFile) throws Exception {
        Scanner scanner = new Scanner(inputFile);
        tokens = scanner.getAllTokens();
        this.makeColoringMap();
    }

    private void makeColoringMap() {
        coloringMap.put(TokenType.RESERVED, ANSI_CYAN);
        coloringMap.put(TokenType.BUILT_IN_TYPES, ANSI_GREEN);
        coloringMap.put(TokenType.SYMBOL, ANSI_WHITE);
        coloringMap.put(TokenType.STRING, ANSI_YELLOW);
        coloringMap.put(TokenType.NUMBER, ANSI_BLUE);
        coloringMap.put(TokenType.QUOTATION, ANSI_PURPLE);
        coloringMap.put(TokenType.COMMENT, ANSI_GREY);
        coloringMap.put(TokenType.WHITE_SPACE, "");
        coloringMap.put(TokenType.EOF, "");
        coloringMap.put(TokenType.ERROR, ANSI_RED);

    }

    public void print() {
        for(Token t : tokens)
            System.out.print(coloringMap.get(t.getType()) + t.getToken() + ANSI_RESET);
    }
}
