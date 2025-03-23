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
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String projectPath = args.length > 0 ? args[0] : ".";
        
        while (true) {
            System.out.println("");
            System.out.print("Introduzca la ruta del proyecto:");
            String inputPath = scanner.nextLine().trim();
            System.out.println("");
            
            if (!inputPath.isEmpty()) {
                projectPath = inputPath;
            }
            
            Checker checkerChain = createCheckerChain();
            CodeProcessor processor = new CodeProcessor(checkerChain);

            List<FileStats> results = analyzeProject(projectPath, processor);
            if (!results.isEmpty()) {
                printResults(results);
            }
            
            System.out.println("");
            System.out.print("¿Desea analizar otra ruta? (y/n): ");
            String respuesta = scanner.nextLine().trim().toLowerCase();
            if (!(respuesta.equals("y") || respuesta.equals("yes"))) {
                System.out.println("");
                break;
            }
        }
        
        scanner.close();
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
        int totalPhysicalLines = 0;

        System.out.printf("%-40s | %-15s", "Archivo", "Lineas Fisicas");
        System.out.println("");
        System.out.println("=".repeat(50));

        for (FileStats stats : results) {
            System.out.printf("%-40s | %-15d", stats.fileName(), stats.physicalLines());
            System.out.println("");
            totalPhysicalLines += stats.physicalLines();
        }

        System.out.println("");
        System.out.println("=".repeat(50));
        System.out.printf("%-40s | %-15d", "Total:", totalPhysicalLines);
    }
}
