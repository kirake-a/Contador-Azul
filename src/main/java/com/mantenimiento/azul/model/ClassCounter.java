package com.mantenimiento.azul.model;

public class ClassCounter {
    private int physicalLOC = 0;
    private String name;
    private int methodCount = 0;
    private int lines = 0;

    public ClassCounter(String name) {
        this.name = name;
    }

    public int getPhysicalLOC() {
        return physicalLOC - 2; // -2 for the class declarations
    }

    public void addPhysicalLOC() {
        this.physicalLOC++;
    }

    public String getName() {
        return name;
    }
    
    public void incrementMethodCount() {
        this.methodCount++;
    }

    public int getMethodCount() {
        return methodCount;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }
}
