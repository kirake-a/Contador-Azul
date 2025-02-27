package com.mantenimiento.azul;

import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import com.mantenimiento.azul.model.FileStats;

import com.mantenimiento.azul.utils.ProjectAnalyzer;

import static org.junit.jupiter.api.Assertions.*;

public class ProjectAnalyzerTest {
    
    @Test
public void projectAnalyzerUnitTest() {

    String wrongFile = "src\\test\\java\\com\\mantenimiento\\azul\\WrongFile.py";
   
    try {

        List<FileStats> obtainedResult;
        obtainedResult = ProjectAnalyzer.analyzeProject(wrongFile);
        assertTrue(obtainedResult.isEmpty());

    } catch (IOException e) {
        // TODO Auto-generated catch block
        e.printStackTrace();
    }
    
    
    
}
}
