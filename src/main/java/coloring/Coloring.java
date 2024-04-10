package coloring;

import scanner.Scanner;
import token.Token;
import token.TokenType;

import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import java.io.BufferedWriter;
public class Coloring {
    List<Token> tokens;
    HashMap<TokenType, String> coloringMap = new HashMap<>();
    HashMap<TokenType, String> htmlColorMap = new HashMap<>();
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
        this.makeHtmlColorMap();
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

    private void makeHtmlColorMap() {
        htmlColorMap.put(TokenType.RESERVED, "#F4A261");
        htmlColorMap.put(TokenType.BUILT_IN_TYPES, "#2A9D8F");
        htmlColorMap.put(TokenType.SYMBOL, "#FFFFFF");
        htmlColorMap.put(TokenType.STRING, "#E9C46A");
        htmlColorMap.put(TokenType.NUMBER, "#00B4D8");
        htmlColorMap.put(TokenType.QUOTATION, "#E76F51");
        htmlColorMap.put(TokenType.COMMENT, "#264653");
        htmlColorMap.put(TokenType.WHITE_SPACE, "");
        htmlColorMap.put(TokenType.EOF, "");
        htmlColorMap.put(TokenType.ERROR, "#D62828");
    }

    public void print() {
        for (Token t : tokens)
            System.out.print(coloringMap.get(t.getType()) + t.getToken() + ANSI_RESET);
    }

    public void printToHtml(String outputFile) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))) {
            writer.write("<!DOCTYPE html><html><head><title>Colored Code</title>");
            writer.write("<style>body { background-color: #000; color: #fff; } pre { white-space: pre-wrap; word-wrap: break-word; } span { color: inherit; }</style>");
            writer.write("</head><body><pre>");

            for (Token t : tokens) {
                String color = htmlColorMap.get(t.getType());
                String token = t.getToken();
                if (!color.isEmpty()) {
                    writer.write(String.format("<span style=\"color:%s;\">%s</span>", color, token));
                } else {
                    writer.write(token);
                }
            }
            writer.write("</pre></body></html>");
        }
    }
}