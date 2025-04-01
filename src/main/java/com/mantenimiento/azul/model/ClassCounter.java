package com.mantenimiento.azul.model;

public class ClassCounter {
    private int physicalLOC = 0;
    private int lines = 0;
    private String name;

    public ClassCounter(String name) {
        this.name = name;
    }

    public int getPhysicalLOC() {
        return physicalLOC;
    }

    public void addPhysicalLOC() {
        this.physicalLOC++;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public String getName() {
        return name;
    }
    

}
