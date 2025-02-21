package com.mantenimiento.azul;

import java.io.*;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.*;

public class CodeLineCounter {
    public static void main(String[] args) throws IOException {
        
        String projectPath = args.length > 0 ? args[0] : "."; 
        List<FileStats> results = new ArrayList<>();
        
        Files.walkFileTree(Paths.get(projectPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".java")) {
                    FileStats stats = countLines(file);
                    if (stats != null) results.add(stats);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        printResults(results);
    }

    private static FileStats countLines(Path file) {
        int physicalLines = 0;
        int logicalLines = 0;
        boolean insideBlockComment = false;
        StringBuilder logicalBuffer = new StringBuilder();

        try (BufferedReader reader = new BufferedReader(new FileReader(file.toFile()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                
                if (line.isEmpty()) continue;

                if (insideBlockComment) {
                    if (line.endsWith("*/")) insideBlockComment = false;
                    continue;
                }
                if (line.startsWith("/*") || line.startsWith("/**")) {
                    insideBlockComment = true;
                    continue;
                }
                if (line.startsWith("//")) continue;

                physicalLines++;

                logicalBuffer.append(line);
                if (line.endsWith(";") || line.endsWith("{")) { 
                    logicalLines++;
                    logicalBuffer.setLength(0); 
                }
            }
        } catch (IOException e) {
            System.err.println("Error al leer el archivo: " + file);
            return null;
        }

        return new FileStats(file.toString(), physicalLines, logicalLines);
    }

    private static void printResults(List<FileStats> results) {
        System.out.printf("%-50s %-15s %-15s%n", "Archivo", "Líneas Físicas", "Líneas Lógicas");
        System.out.println("=".repeat(80));
        for (FileStats stats : results) {
            System.out.printf("%-50s %-15d %-15d%n", stats.fileName, stats.physicalLines, stats.logicalLines);
        }
    }

    static class FileStats {
        String fileName;
        int physicalLines;
        int logicalLines;

        public FileStats(String fileName, int physicalLines, int logicalLines) {
            this.fileName = fileName;
            this.physicalLines = physicalLines;
            this.logicalLines = logicalLines;
        }
    }
}
