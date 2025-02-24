package com.mantenimiento.azul.checker;

import java.util.List;
import java.util.regex.Pattern;

import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.utils.Regex;

public class LeftCurlyBraceChecker extends Checker {

    public LeftCurlyBraceChecker() {
        this.pattern = Pattern.compile(Regex.REGEX_ONLY_LEFT_CURLY_BARCE);
    }

    @Override
    public void pass(List<String> codeStream) throws InvalidLineFormatException {

        boolean pass = codeStream.stream().anyMatch(line -> this.pattern.matcher(line).matches());
        if (pass) {
            throw new InvalidLineFormatException("No se permite tener la llave abierta como único elemento de la línea");
        } else if (this.next != null) {
            this.next.pass(codeStream);
        }
    }

}
