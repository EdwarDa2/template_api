package org.EdwarDa2.model;

public class Producto {
    private int id_producto;
    private String nombre;
    private float precio ;
    private int id_categoria;
    private int id_subCategoria;

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public int getId_subCategoria() {
        return id_subCategoria;
    }

    public void setId_subCategoria(int id_subCategoria) {
        this.id_subCategoria = id_subCategoria;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public float getPrecio() {
        return precio;
    }

    public void setPrecio(float precio) {
        this.precio = precio;
    }
}
