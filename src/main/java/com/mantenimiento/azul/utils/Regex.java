package com.mantenimiento.azul.utils;

import java.util.regex.Pattern;

public class Regex {
    public static final String REGEX_ONLY_LEFT_CURLY_BARCE = "\\s*\\{\\s*$";
    public static final String REGEX_ENDS_WITH_PARENTHESES = ".*[\\(\\)]$";
    public static final String REGEX_MULTI_INSTANCE = "\\s*\\w+\\s+\\w+(\\s*=\\s*[^,;]+)?(\\s*,\\s*\\w+(\\s*=\\s*[^,;]+)?)*\\s*;";
    public static final String REGEX_ENDS_WITH_WORD = ".*\\b\\w+\\b$";
    
    public static final Pattern SINGLE_LINE_COMMENT = Pattern.compile("//.*");
    public static final Pattern BLOCK_COMMENT_START = Pattern.compile("/\\*.*");
    public static final Pattern BLOCK_COMMENT_END = Pattern.compile(".*\\*/");
    public static final Pattern LOGICAL_LINE = Pattern.compile(".*[;{]$");

}
