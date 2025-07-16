package org.EdwarDa2.model;

import java.util.ArrayList;

public class Stats {
    private String label;
    private float value;

    public Stats(String label, float data) {
        this.label = label;
        this.value = data;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }
}
