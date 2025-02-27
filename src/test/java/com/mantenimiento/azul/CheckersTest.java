package com.mantenimiento.azul;

import org.junit.jupiter.api.Test;

import java.nio.file.*;
import com.mantenimiento.azul.checker.Checker;
import com.mantenimiento.azul.checker.EndBreakChecker;
import com.mantenimiento.azul.checker.LeftCurlyBraceChecker;
import com.mantenimiento.azul.checker.MultiInstanceChecker;
import com.mantenimiento.azul.checker.ParenthesesChecker;
import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.processor.CodeProcessor;

import static org.junit.jupiter.api.Assertions.*;

public class CheckersTest {

    @Test
    void EndBreakCheckerUnitTest(){

        Path EndBreakCheckerTestFile = Paths.get("src\\test\\java\\com\\mantenimiento\\azul\\EndBreakTest.java");

        Checker checkerChain = createCheckerChain();
        CodeProcessor codeProcessor = new CodeProcessor(checkerChain);
        
        assertThrows(InvalidLineFormatException.class,() -> {
            codeProcessor.processFile(EndBreakCheckerTestFile);
        });
        
    }
    @Test
    void LeftCurlyBraceCheckerUnitTest(){

        Path EndBreakCheckerTestFile = Paths.get("src\\test\\java\\com\\mantenimiento\\azul\\LeftCurlyBraceTest.java");

        Checker checkerChain = createCheckerChain();
        CodeProcessor codeProcessor = new CodeProcessor(checkerChain);
        
        assertThrows(InvalidLineFormatException.class,() -> {
            codeProcessor.processFile(EndBreakCheckerTestFile);
        });
        
    }

    @Test
    void MultiInstanceCheckerUnitTest(){

        Path EndBreakCheckerTestFile = Paths.get("src\\test\\java\\com\\mantenimiento\\azul\\MultiInstanceTest.java");

        Checker checkerChain = createCheckerChain();
        CodeProcessor codeProcessor = new CodeProcessor(checkerChain);
        
        assertThrows(InvalidLineFormatException.class,() -> {
            codeProcessor.processFile(EndBreakCheckerTestFile);
        });
        
    }

    @Test
    void ParenthesesCheckerUnitTest(){

        Path parenthesesTestFile = Paths.get("src\\test\\java\\com\\mantenimiento\\azul\\ParenthesesTest.java");

        Checker checkerChain = createCheckerChain();
        CodeProcessor codeProcessor = new CodeProcessor(checkerChain);
        
        assertThrows(InvalidLineFormatException.class,() -> {
            codeProcessor.processFile(parenthesesTestFile);
        });
        
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