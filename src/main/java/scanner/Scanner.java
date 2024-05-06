package scanner;

import token.*;

import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Scanner {

    private FileReader inputFile;
    private StringBuilder token;
    private char nextChar;

    public Scanner(String sourceFile) throws Exception {
        inputFile = new FileReader(sourceFile);
        nextChar = (char)inputFile.read();
    }

    public Token nextToken() throws IOException {
        token = new StringBuilder();

        if(nextChar == 65535)
            return new Token("", TokenType.EOF);

        if(Character.isWhitespace(nextChar)) {
            token.append(nextChar);
            nextChar = (char)inputFile.read();

            return new Token(token.toString(), TokenType.WHITE_SPACE);
        }

        if(Symbol.isSymbol(Character.toString(nextChar))) {
            token.append(nextChar);

            switch(nextChar) {
                case '!', '=', '>', '<':
                    nextChar = (char)inputFile.read();
                    if(nextChar == '=') {
                        token.append(nextChar);
                        nextChar = (char)inputFile.read();
                    }
                    return new Token(token.toString(), TokenType.SYMBOL);
                case '|':
                    nextChar = (char)inputFile.read();
                    if(nextChar == '|')
                        token.append(nextChar);
                    return new Token(token.toString(), TokenType.SYMBOL);
                case '&':
                    nextChar = (char)inputFile.read();
                    if(nextChar == '&')
                        token.append(nextChar);
                    return new Token(token.toString(), TokenType.SYMBOL);
                case '/':
                    nextChar = (char)inputFile.read();
                    if(nextChar == '/') {
                        token.append(nextChar);
                        nextChar = (char)inputFile.read();

                        while(nextChar != '\n') {
                            token.append(nextChar);
                            nextChar = (char)inputFile.read();
                        }
                        return new Token(token.toString(), TokenType.COMMENT);
                    } else {
                        nextChar = (char)inputFile.read();
                        return new Token(token.toString(), TokenType.SYMBOL);
                    }
                case '"':
                    nextChar = (char) inputFile.read();
                    char prevChar = 0;

                    while (nextChar != '"' || prevChar == '\\') {
                        token.append(nextChar);
                        prevChar = nextChar;
                        nextChar = (char) inputFile.read();
                    }
                    token.append(nextChar);
                    nextChar = (char) inputFile.read();
                    return new Token(token.toString(), TokenType.QUOTATION);
                case '\'':
                    nextChar = (char)inputFile.read();

                    while(nextChar != '\'') {
                        token.append(nextChar);
                        nextChar = (char)inputFile.read();
                    }

                    token.append(nextChar);
                    nextChar = (char)inputFile.read();

                    return new Token(token.toString(), TokenType.QUOTATION);
                default:
                    nextChar = (char)inputFile.read();
                    return new Token(token.toString(), TokenType.SYMBOL);
            }
        }

        if (Character.isDigit(nextChar)) {
            token.append(nextChar);
            nextChar = (char)inputFile.read();

            while (nextChar != 65535 && Character.isDigit(nextChar)) {
                token.append(nextChar);
                nextChar = (char)inputFile.read();
            }
            return new Token(token.toString(), TokenType.NUMBER);
        }

        if (Character.isLetter(nextChar)) {
            token.append(nextChar);
            nextChar = (char)inputFile.read();

            while (nextChar != 65535 && (Character.isLetter(nextChar) || nextChar == '_')) {
                token.append(nextChar);
                nextChar = (char)inputFile.read();
            }

            if(Reserved.isReserved(token.toString()))
                return new Token(token.toString(), TokenType.RESERVED);

            if(BuiltInTypes.isBuiltInType(token.toString()))
                return new Token(token.toString(), TokenType.BUILT_IN_TYPES);

            return new Token(token.toString(), TokenType.STRING);
        }

        token.append(nextChar);
        nextChar = (char)inputFile.read();
        return new Token(token.toString(), TokenType.ERROR);
    }

    public List<Token> getAllTokens() {
        List<Token> tokens = new ArrayList<>();
        Token token_;
        boolean errorOccurred = false;

        try {
            // Skanowanie wszystkich tokenów
            while ((token_ = this.nextToken()).getType() != TokenType.EOF) {
                tokens.add(token_);
            }
        } catch (IOException e) {
            errorOccurred = true;
            System.out.println("Scanner error: " + e.getMessage());
        }

        // Wyświetlanie tokenów, tylko jeśli nie wystąpił żaden błąd
        if (!errorOccurred) {
            return tokens;
        }

        return null;
    }
}
