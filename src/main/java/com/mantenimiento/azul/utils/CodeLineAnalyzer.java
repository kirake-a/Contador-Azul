package com.mantenimiento.azul.utils;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Arrays;

import com.mantenimiento.azul.model.*;

public class CodeLineAnalyzer {

    public static FileStats analyzeFile(Path filePath) {
        int physicalLines = 0;
        //int logicalLines = 0;
        boolean insideBlockComment = false;
        String currentClassName;
        ArrayList<ClassCounter> classCounter = new ArrayList<ClassCounter>();
        ClassCounter currentClass = null;
        int lines = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // ---------------- Lines counter ---------------- //
                lines++;

                // ---------------- blank lines ---------------- //
                if (line.isEmpty()) continue;
                
                // ---------------- lines or blocks of comments ---------------- //
                if (insideBlockComment) {
                    if (Regex.BLOCK_COMMENT_END.matcher(line).matches()) {
                        insideBlockComment = false;
                    }
                    continue;
                }
                if (Regex.BLOCK_COMMENT_START.matcher(line).matches()) {
                    insideBlockComment = true;
                    continue;
                }
                if (Regex.BLOCK_COMMENT_CONTINUE.matcher(line).matches()) {
                    continue;
                }

                if (Regex.SINGLE_LINE_COMMENT.matcher(line).matches()) continue;

                // ---------------- Class identification ---------------- //
                if (Regex.CLASS_IDENTIFIER.matcher(line).matches()) {
                    String[] classLine = line.split(" ");
                    int indexClassWord = Arrays.asList(classLine).indexOf("class");
                    currentClassName = classLine[indexClassWord + 1];
                    currentClass = new ClassCounter(currentClassName);
                    classCounter.add(currentClass);
                }

                // ---------------- record identification ---------------- //
                if (Regex.RECORD_IDENTIFIER.matcher(line).matches()) {
                    String[] recordLine = line.split(" ");
                    int indexClassWord = Arrays.asList(recordLine).indexOf("record");
                    currentClassName = recordLine[indexClassWord + 1];
                    currentClass = new ClassCounter(currentClassName);
                    classCounter.add(currentClass);
                }

                if (currentClass != null) {
                    currentClass.addPhysicalLOC();
                }

                // ------------- Methods counter --------- //
                if (currentClass != null && Regex.METHOD_IDENTIFIER.matcher(line).matches()) {
                    currentClass.incrementMethodCount();
                }
                
                if(line.contains("else") || line.contains("catch") || line.contains("finally")){
                    continue;
                }

                // ---------------- Physical line counter ---------------- //
                physicalLines++; 
            }

            if (currentClass!=null) {
                currentClass.setLines(lines);
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            return null;
        }

        return new FileStats(filePath.toString(), physicalLines, lines, classCounter);
    }
}