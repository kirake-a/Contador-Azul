package com.mantenimiento.azul.utils;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

import com.mantenimiento.azul.Models.FileStats;

public class ProjectAnalyzer {

    public static List<FileStats> analyzeProject(String projectPath) throws IOException {
        List<FileStats> results = new ArrayList<>();

        Files.walkFileTree(Paths.get(projectPath), new SimpleFileVisitor<Path>() {
            @Override
            public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                if (file.toString().endsWith(".java")) {
                    FileStats stats = CodeLineAnalyzer.analyzeFile(file);
                    if (stats != null) results.add(stats);
                }
                return FileVisitResult.CONTINUE;
            }
        });

        return results;
    }
}
