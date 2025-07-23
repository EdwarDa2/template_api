package org.EdwarDa2.model;

public class Admin {
    private int id_admin;
    private int id_usuario;
    private String clave;

    public Admin() {
    }

    public Admin(int id_usuario, String clave) {
        this.id_usuario = id_usuario;
        this.clave = clave;
    }

    public int getId_admin() {
        return id_admin;
    }

    public void setId_admin(int id_admin) {
        this.id_admin = id_admin;
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
