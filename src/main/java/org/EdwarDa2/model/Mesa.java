package org.EdwarDa2.model;

public class Mesa {
    private int id_mesa;
    private int num_mesa;
    private boolean status;
    private int id_mesero;
    private int id_total;

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getNum_mesa() {
        return num_mesa;
    }

    public void setNum_mesa(int num_mesa) {
        this.num_mesa = num_mesa;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getId_mesero() {
        return id_mesero;
    }

    public void setId_mesero(int id_mesero) {
        this.id_mesero = id_mesero;
    }

    public int getId_total() {
        return id_total;
    }

    public void setId_total(int id_total) {
        this.id_total = id_total;
    }
}
