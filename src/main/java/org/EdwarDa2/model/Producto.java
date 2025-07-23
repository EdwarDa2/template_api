package org.EdwarDa2.model;

public class Producto {
    private int id_producto;
    private int id_subCategoria;
    private String nombre;
    private float precio ;
    private Subcategoria subcategoria;

    public Subcategoria getSubcategoria() {return subcategoria;}

    public void setSubcategoria(Subcategoria subcategoria) {this.subcategoria = subcategoria;}

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
