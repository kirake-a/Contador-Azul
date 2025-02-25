package com.mantenimiento.azul.processor;

import com.mantenimiento.azul.checker.Checker;
import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.model.FileStats;
import com.mantenimiento.azul.utils.CodeLineAnalyzer;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

public class CodeProcessor {

    private final Checker checkerChain;

    public CodeProcessor(Checker checkerChain) {
        this.checkerChain = checkerChain;
    }

    public FileStats processFile(Path filePath) throws IOException, InvalidLineFormatException {
        List<String> codeLines = Files.readAllLines(filePath);

        if (checkerChain != null) {
            checkerChain.pass(codeLines);
        }

        return CodeLineAnalyzer.analyzeFile(filePath);
    }
}
