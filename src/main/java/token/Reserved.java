package token;

public enum Reserved {
    BREAK("break"),
    CASE("case"),
    CLASS("class"),
    CONST("const"),
    ELSE("else"),
    ENUM("enum"),
    EXTENDS("extends"),
    FINAL("final"),
    FOR("for"),
    IF("if"),
    IMPORT("import"),
    NEW("new"),
    PACKAGE("package"),
    PRIVATE("private"),
    PROTECTED("protected"),
    PUBLIC("public"),
    RETURN("return"),
    STATIC("static"),
    SWITCH("switch"),
    THIS("this"),
    THROW("throw"),
    THROWS("throws"),
    TRY("try"),
    WHILE("while");
    private final String token;

    Reserved(String symbol) {
        this.token = symbol;
    }

    public static boolean isReserved(String token) {
        for (Reserved punctuator : Reserved.values()) {
            if (punctuator.token.equals(token)) {
                return true;
            }
        }
        return false;
    }
}
