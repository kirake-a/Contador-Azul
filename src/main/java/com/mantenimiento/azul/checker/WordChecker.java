package com.mantenimiento.azul.checker;

import java.util.List;
import java.util.regex.Pattern;

import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.regex.Regex;



public class WordChecker extends Checker {

    public WordChecker() {
        this.pattern = Pattern.compile(Regex.REGEX_ENDS_WITH_WORD);
    }

    @Override
    public void pass(List<String> codeStream) throws InvalidLineFormatException {

        boolean pass = codeStream.stream().anyMatch(line -> this.pattern.matcher(line).matches());
        if (pass) {
            throw new InvalidLineFormatException("No se permite terminar la línea de códgo con palabras");
        } else if (this.next != null) {
            this.next.pass(codeStream);
        }
    }

}
