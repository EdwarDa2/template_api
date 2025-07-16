package org.EdwarDa2.DTO;

import java.util.ArrayList;

public class StatsDTO {
    ArrayList<String> labels= new ArrayList<>();
    ArrayList<Float> data= new ArrayList<>();

    public StatsDTO(ArrayList<String> labels, ArrayList<Float> data) {
        this.labels = labels;
        this.data = data;
    }
}
