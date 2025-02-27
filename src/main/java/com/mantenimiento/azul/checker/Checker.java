package com.mantenimiento.azul.checker;

import java.util.List;
import com.mantenimiento.azul.exception.InvalidLineFormatException;

public abstract class Checker {
    protected Checker next;

    public void setNext(Checker checker) {
        this.next = checker;
    }

    public abstract void pass(List<String> codeList) throws InvalidLineFormatException;
}