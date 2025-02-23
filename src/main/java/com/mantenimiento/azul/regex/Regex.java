package com.mantenimiento.azul.regex;


public class Regex {
    public static final String REGEX_ONLY_LEFT_CURLY_BARCE = "\\s*\\{\\s*$";
    public static final String REGEX_ENDS_WITH_PARENTHESES = ".*[\\(\\)]$";
    public static final String REGEX_MULTI_INSTANCE = "\\s*\\w+\\s+\\w+(\\s*=\\s*[^,;]+)?(\\s*,\\s*\\w+(\\s*=\\s*[^,;]+)?)*\\s*;";
    public static final String REGEX_ENDS_WITH_WORD = ".*\\b\\w+\\b$";
}
