package com.mantenimiento.azul.checker;

import java.util.List;
import com.mantenimiento.azul.exception.InvalidLineFormatException;
import com.mantenimiento.azul.utils.Regex;

public class LeftCurlyBraceChecker extends Checker {

    @Override
    public void pass(List<String> codeStream) throws InvalidLineFormatException {

        boolean pass = codeStream.stream().anyMatch(line -> Regex.ONLY_LEFT_CURLY_BARCE.matcher(line).matches());
        if (pass) {
            throw new InvalidLineFormatException("No se permite tener la llave abierta como único elemento de la línea");
        } else if (this.next != null) {
            this.next.pass(codeStream);
        }
    }

}
