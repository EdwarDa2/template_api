package org.EdwarDa2.model;

public class Mesero {
    private int id_mesero;
    private int id_usuario;
    private String clave;

    public Mesero() {
    }

    public Mesero(int id_usuario, String clave) {
        this.id_usuario = id_usuario;
        this.clave = clave;
    }

    public int getId_mesero() {
        return id_mesero;
    }

    public void setId_mesero(int id_mesero) {
        this.id_mesero = id_mesero;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }
}
