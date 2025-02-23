package com.mantenimiento.azul.checker;

import java.util.List;
import java.util.regex.Pattern;


import com.mantenimiento.azul.exception.InvalidLineFormatException;


public abstract class Checker {
    protected Checker next;
    protected Pattern pattern; 
    
    public void setNext(Checker checker){
        this.next = checker;
    }
    
    public abstract void pass(List<String> codeList) throws InvalidLineFormatException;
}