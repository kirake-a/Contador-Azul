package com.mantenimiento.azul.utils;

import java.io.*;
import java.nio.file.Path;

import com.mantenimiento.azul.model.FileStats;

public class CodeLineAnalyzer {

    public static FileStats analyzeFile(Path filePath) {
        int physicalLines = 0;
        boolean insideBlockComment = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();

                // Evita lineas en blanco
                if (line.isEmpty()) continue;
                
                // Evita lineas con comentarios
                if (insideBlockComment) {
                    if (Regex.BLOCK_COMMENT_END.matcher(line).matches()) {
                        insideBlockComment = false;
                    }
                    continue;
                }

                // identificar inicio de un comentario de bloque
                if (Regex.BLOCK_COMMENT_START.matcher(line).matches()) {
                    insideBlockComment = true;
                    continue;
                }

                // identificar comentarios de una sola linea
                if (Regex.SINGLE_LINE_COMMENT.matcher(line).matches()) continue;

                physicalLines++; // incrementa el contador de lineas fisicas
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + filePath);
            return null;
        }

        return new FileStats(filePath.toString(), physicalLines);
    }
}