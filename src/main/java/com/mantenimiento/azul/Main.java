package com.mantenimiento.azul;

import com.mantenimiento.azul.checker.MultiInstanceChecker;
import com.mantenimiento.azul.checker.ParenthesesChecker;
import com.mantenimiento.azul.checker.Checker;
import com.mantenimiento.azul.checker.EndBreakChecker;
import com.mantenimiento.azul.checker.LeftCurlyBraceChecker;
import com.mantenimiento.azul.processor.CodeProcessor;
import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.model.FileStats;

import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.ArrayList;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String projectPath = args.length > 0 ? args[0] : ".";
        

        Checker checkerChain = createCheckerChain();
        CodeProcessor processor = new CodeProcessor(checkerChain);

        List<FileStats> results = analyzeProject(projectPath, processor);
        printResults(results);
    }
    
    private static Checker createCheckerChain() {
        ParenthesesChecker parenthesesChecker = new ParenthesesChecker();
        LeftCurlyBraceChecker leftCurlyBraceChecker = new LeftCurlyBraceChecker();
        MultiInstanceChecker multiInstanceChecker = new MultiInstanceChecker();
        EndBreakChecker endBreakChecker = new EndBreakChecker();

        parenthesesChecker.setNext(leftCurlyBraceChecker);
        leftCurlyBraceChecker.setNext(multiInstanceChecker);
        multiInstanceChecker.setNext(endBreakChecker);
      
        
        return parenthesesChecker;
    }
    
    private static List<FileStats> analyzeProject(String projectPath, CodeProcessor processor) {
        List<FileStats> results = new ArrayList<>();

        try {
            Files.walkFileTree(Paths.get(projectPath), new SimpleFileVisitor<Path>() {
                @Override
                public FileVisitResult visitFile(Path file, BasicFileAttributes attrs) {
                    if (file.toString().endsWith(".java")) {
                        processFile(file, processor, results);
                    }
                    return FileVisitResult.CONTINUE;
                }
            });
        } catch (IOException e) {
            System.err.println("Error al analizar el proyecto: " + e.getMessage());
        }

        return results;
    }

    private static void processFile(Path file, CodeProcessor processor, List<FileStats> results) {
        try {
            FileStats stats = processor.processFile(file);
            results.add(stats);
        } catch (InvalidLineFormatException e) {
            System.err.println("Error en " + file + ": " + e.getMessage());
        } catch (IOException e) {
            System.err.println("No se pudo leer " + file);
        }
    }

    private static void printResults(List<FileStats> results) {
        System.out.printf("%-50s %-15s %-15s%n", "Archivo", "Líneas Físicas", "Líneas Lógicas");
        System.out.println("=".repeat(80));

        for (FileStats stats : results) {
            System.out.printf("%-50s %-15d %-15d%n", stats.fileName(), stats.physicalLines(), stats.logicalLines());
        }
    }
}
