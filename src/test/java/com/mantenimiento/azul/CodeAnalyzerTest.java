package com.mantenimiento.azul;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import com.mantenimiento.azul.model.FileStats;

import com.mantenimiento.azul.utils.ProjectAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

public class CodeAnalyzerTest {
    
    @Test
public void codeAnalyzerUnitTest() {
    String regularFileTest = "src\\test\\java\\com\\mantenimiento\\azul\\RegularFile.java";

    List<FileStats> expectedResult = List.of(
        new FileStats(regularFileTest, 6, 4)
    );

    try {
        List<FileStats> obtainedResult = ProjectAnalyzer.analyzeProject(regularFileTest);
        
        assertEquals(expectedResult.size(), obtainedResult.size());
        assertEquals(expectedResult.get(0), obtainedResult.get(0));
        System.out.println("se espera " + expectedResult.get(0));
        System.out.println("Se obtuvo" + obtainedResult.get(0));
    } catch (IOException e) {

        fail("Se produjo una excepción inesperada: " + e.getMessage());
    }
    
}
}
