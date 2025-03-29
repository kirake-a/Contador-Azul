package com.mantenimiento.azul.model;

import java.nio.file.Paths;
import java.util.ArrayList;

public record FileStats(String fileName, int physicalLines, int logicalLines, ArrayList<ClassCounter> classes) {
    public String fileName() {
        return Paths.get(fileName).getFileName().toString();
    }
}
