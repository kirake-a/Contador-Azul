package com.mantenimiento.azul.utils;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

import com.mantenimiento.azul.model.*;

public class CodeLineAnalyzer {

    public static FileStats analyzeFile(Path filePath) {
        int physicalLines = 0;
        int classLineCounter = 0;
        boolean insideBlockComment = false;
        boolean firstClass = true;
        ArrayList<ClassCounter> classCounter = new ArrayList<ClassCounter>();
        int classIndex = 0;
        ClassCounter currentClass = null;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // ---------------- lineas en blanco ---------------- //
                if (line.isEmpty()) continue;
                
                // ---------------- lineas con comentarios ---------------- //
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

                // ---------------- Identificación de clases ---------------- //

                if (Regex.CLASS_IDENTIFIER.matcher(line).matches()) {
                    String[] classLine = line.split(" ");
                    currentClass = new ClassCounter(classLine[2]);
                    classCounter.add(currentClass);
                }

                if (currentClass != null) {
                    currentClass.addPhysicalLOC();
                }

                // ---------------- lineas en blanco ---------------- //
                physicalLines++; 
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            return null;
        }

        return new FileStats(filePath.toString(), physicalLines, classCounter);
    }
}