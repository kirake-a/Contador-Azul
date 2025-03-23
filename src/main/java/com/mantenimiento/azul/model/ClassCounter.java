package com.mantenimiento.azul.model;

public class ClassCounter {
    private int physicalLOC = 0;
    private String name;

    public ClassCounter(String name) {
        this.name = name;
    }

    public int getPhysicalLOC() {
        return physicalLOC;
    }

    public void setPhysicalLOC(int physicalLOC) {
        this.physicalLOC = physicalLOC;
    }

    public void addPhysicalLOC() {
        this.physicalLOC++;
    }

    public String getName() {
        return name;
    }
    

}
