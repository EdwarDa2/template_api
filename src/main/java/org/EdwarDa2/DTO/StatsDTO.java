package org.EdwarDa2.DTO;


import java.util.List;

public class StatsDTO {
    List<String> name;
    List<Float> price;
    List<Integer> amount;
    public StatsDTO(List<String> name, List<Float> price, List<Integer> amount) {
        this.name = name;
        this.price = price;
        this.amount = amount;
    }
    public List<String> getName() {
        return name;
    }

    public void setName(List<String> name) {
        this.name = name;
    }

    public List<Float> getPrice() {
        return price;
    }

    public void setPrice(List<Float> price) {
        this.price = price;
    }

    public List<Integer> getAmount() {
        return amount;
    }

    public void setAmount(List<Integer> amount) {
        this.amount = amount;
    }
}
