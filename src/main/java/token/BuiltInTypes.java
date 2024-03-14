package token;

public enum BuiltInTypes {
    INT("int"),
    CHAR("char"),
    BYTE("byte"),
    SHORT("short"),
    LONG("long"),
    FLOAT("float"),
    DOUBLE("double"),
    BOOLEAN("boolean");
    private final String token;

    BuiltInTypes(String symbol) {
        this.token = symbol;
    }

    public static boolean isBuiltInType(String token) {
        for (BuiltInTypes b : BuiltInTypes.values()) {
            if (b.token.equals(token)) {
                return true;
            }
        }
        return false;
    }
}
