package org.EdwarDa2.model;
public class    User {
    private int id_usuario;
    private String nombre;
    private String apellido_p;
    private String apellido_m;
    private Integer rol; // Tipo de usuario (rol)

    public User() {
    }

    public User(String nombre, String apellido_p, String apellido_m, Integer rol) {
        this.nombre = nombre;
        this.apellido_p = apellido_p;
        this.apellido_m = apellido_m;
        this.rol = rol;
    }

    // Getters y setters


    public int getId_usuario() {
        return id_usuario;
    }

    public void setId_usuario(int id_usuario) {
        this.id_usuario = id_usuario;
    }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido_p() { return apellido_p; }
    public void setApellido_p(String apellido_p) { this.apellido_p = apellido_p; }

    public String getApellido_m() { return apellido_m; }
    public void setApellido_m(String apellido_m) { this.apellido_m = apellido_m; }

    public Integer getRol() { return rol; }
    public void setRol(Integer rol) { this.rol = rol; }
}

