package token;

public enum Symbol {
    OPEN_PARENTHESIS("("),
    CLOSE_PARENTHESIS(")"),
    OPEN_BRACE("{"),
    CLOSE_BRACE("}"),
    OPEN_BRACKET("["),
    CLOSE_BRACKET("]"),
    COMMA(","),
	SEMICOLON(";"),
	PLUS("+"),
	MINUS("-"),
	MULTIPLICATION("*"),
	DIVISION("/"),
	EQUAL("=="),
	NOT_EQUAL("!="),
	GREATER(">"),
	GREATER_OR_EQUAL(">="),
	LESSER("<"),
	LESSER_OR_EQUAL("<="),
	IS("="),
    NOT("!"),
	AND("&&"),
    HALF_AND("&"),
	OR("||"),
    HALF_OR("|"),
    DOT("."),
    QUOTATION("\"");
    private final String token;

    Symbol(String symbol) {
        this.token = symbol;
    }

    public static boolean isSymbol(String token) {
        for (Symbol s : Symbol.values()) {
            if (s.token.equals(token)) {
                return true;
            }
        }
        return false;
    }
}
