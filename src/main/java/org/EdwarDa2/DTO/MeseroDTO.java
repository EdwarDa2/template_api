package org.EdwarDa2.DTO;

public class MeseroDTO {
    private String nombre;
    private int id_usuario;
    private int clave;
    private int id_mesero;
    private Boolean rol;

    public MeseroDTO(int id_usuario, String nombre, int clave, int id_mesero, Boolean rol) {
        this.id_usuario = id_usuario;
        this.nombre = nombre;
        this.clave = clave;
        this.id_mesero = id_mesero;
        this.rol = false;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public int getClave() {
        return clave;
    }

    public void setClave(int clave) {
        this.clave = clave;
    }

    public int getId_mesero() {
        return id_mesero;
    }

    public void setId_mesero(int id_mesero) {
        this.id_mesero = id_mesero;
    }

    public Boolean getRol() {
        return rol;
    }

    public void setRol(Boolean rol) {
        this.rol = rol;
    }
}
