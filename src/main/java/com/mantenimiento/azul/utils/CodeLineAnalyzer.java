package com.mantenimiento.azul.utils;

import java.io.*;
import java.nio.file.Path;

import com.mantenimiento.azul.model.FileStats;

public class CodeLineAnalyzer {

    public static FileStats analyzeFile(Path filePath) {
        int physicalLines = 0;
        int logicalLines = 0;
        boolean insideBlockComment = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                if (line.isEmpty()) continue;
                
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
                if (Regex.SINGLE_LINE_COMMENT.matcher(line).matches()) continue;

                physicalLines++;
                if(line.contains("else") || line.contains("catch") || line.contains("finally")){
                    continue;
                }
                if (Regex.LOGICAL_LINE.matcher(line).matches()) {
                    logicalLines++;
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            return null;
        }

        return new FileStats(filePath.toString(), physicalLines, logicalLines);
    }
}
