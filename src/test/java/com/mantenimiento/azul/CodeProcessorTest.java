package com.mantenimiento.azul;

import org.junit.jupiter.api.Test;

import com.mantenimiento.azul.checker.Checker;
import com.mantenimiento.azul.checker.EndBreakChecker;
import com.mantenimiento.azul.checker.LeftCurlyBraceChecker;
import com.mantenimiento.azul.checker.MultiInstanceChecker;
import com.mantenimiento.azul.checker.ParenthesesChecker;
import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.model.FileStats;
import com.mantenimiento.azul.processor.CodeProcessor;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

public class CodeProcessorTest {

    @Test
    public void CodeProcessorIntegrationTest(){

        String regularFileTest = "src\\test\\java\\com\\mantenimiento\\azul\\RegularFile.java";
        Path processorNeededFile = Paths.get(regularFileTest);
        FileStats expectedResult = new FileStats(regularFileTest, 14, 4);

        Checker checkerChain = createCheckerChain();
        CodeProcessor codeProcessor = new CodeProcessor(checkerChain);

        try {

            FileStats obtainedResult =  codeProcessor.processFile(processorNeededFile);
            assertEquals(expectedResult,obtainedResult);
            System.out.println("El resultado esperado era" + expectedResult);
            System.out.println("Y el resultado obtenido fue " + obtainedResult);

        } catch (IOException e) {

            e.getMessage();

        } catch (InvalidLineFormatException e) {

            e.getMessage();
        }

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

}
