package com.mantenimiento.azul.model;

import java.nio.file.Paths;

public record FileStats(String fileName, int physicalLines) {
    public String fileName() {
        return Paths.get(fileName).getFileName().toString();
    }
}
