package com.mantenimiento.azul;

import org.junit.jupiter.api.Test;

import java.nio.file.Path;
import java.nio.file.Paths;

import com.mantenimiento.azul.model.FileStats;
import com.mantenimiento.azul.utils.CodeLineAnalyzer;


import static org.junit.jupiter.api.Assertions.*;

public class CodeLineAnalyzerTest {
    
    @Test
public void codeAnalyzerUnitTest() {

    String regularFileTest = "src\\test\\java\\com\\mantenimiento\\azul\\RegularFile.java";
    Path testPath = Paths.get(regularFileTest);

    FileStats expectedResult = new FileStats(regularFileTest, 6, 4);
    FileStats obtainedResults = CodeLineAnalyzer.analyzeFile(testPath);
    
    assertEquals(expectedResult, obtainedResults);
    
}
}
