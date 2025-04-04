package com.mantenimiento.azul.utils;

import java.util.regex.Pattern;

public class Regex {

    public static final Pattern ONLY_LEFT_CURLY_BARCE = Pattern.compile("\\s*\\{\\s*$");
    public static final Pattern ENDS_WITH_PARENTHESES = Pattern.compile(".*[\\(\\)]$");
    public static final Pattern MULTI_INSTANCE = Pattern.compile("\\s*\\w+\\s+\\w+\\s*,\\s*\\w+(\\s*=\\s*[^,;]+)?(\\s*,\\s*\\w+(\\s*=\\s*[^,;]+)?)*\\s*;");
    public static final Pattern ENDS_WITH_WORD = Pattern.compile(".*\\b\\w+\\b$");
    
    public static final Pattern SINGLE_LINE_COMMENT = Pattern.compile("//.*");

    public static final Pattern BLOCK_COMMENT_START = Pattern.compile("/\\*.*");
    public static final Pattern BLOCK_COMMENT_CONTINUE = Pattern.compile("^\\s*\\*.*$");
    public static final Pattern BLOCK_COMMENT_END = Pattern.compile(".*\\*/");

    public static final Pattern ENDS_WITH_BREAK =  Pattern.compile(".*;\\s*\\}.*");

    public static final Pattern LOGICAL_LINE = Pattern.compile(".*[{]$");
    
    public static final Pattern CLASS_IDENTIFIER =  Pattern.compile(".*\\bclass\\b\\s\\w+.*");

    public static final Pattern METHOD_IDENTIFIER = 
    Pattern.compile("(@\\w+\\s*)?(public|private|protected)?\\s*(static\\s*)?(abstract\\s*)?\\w+\\s+\\w+\\s*\\([^)]*\\)\\s*(throws\\s+[\\w\\s,]+)?\\s*(\\{|;)");

    public static final Pattern RECORD_IDENTIFIER =  Pattern.compile(".*\\brecord\\b\\s\\w+.*");
}
