package com.mantenimiento.azul.checker;

import java.util.List;
import java.util.regex.Pattern;

import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.utils.Regex;

public class MultiInstanceChecker extends Checker{

    public MultiInstanceChecker () {
        this.pattern = Pattern.compile(Regex.REGEX_MULTI_INSTANCE);
    }

    @Override
    public void pass(List<String> codeStream) throws InvalidLineFormatException {
       
        boolean pass = codeStream.stream().anyMatch(line -> this.pattern.matcher(line).matches());
       if (pass) {
        throw new InvalidLineFormatException("No se permite instanciar varias variables en una sola línea");
       } else if (this.next != null) {
        this.next.pass(codeStream);
       }
    }
    
}
