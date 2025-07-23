package org.EdwarDa2.model;

public class Subcategoria {
    private int id_subcategoria;
    private String nombre_subcategoria;
    private int id_categoria;
    private Categoria categoria;

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria() {
    }

    public Subcategoria(int id_subcategoria, String nombre_subcategoria, int id_categoria) {
        this.id_subcategoria = id_subcategoria;
        this.nombre_subcategoria = nombre_subcategoria;
        this.id_categoria = id_categoria;
    }

    public int getId_subcategoria() {
        return id_subcategoria;
    }

    public void setId_subcategoria(int id_subcategoria) {
        this.id_subcategoria = id_subcategoria;
    }

    public String getNombre_subcategoria() {
        return nombre_subcategoria;
    }

    public void setNombre_subcategoria(String nombre_subcategoria) {
        this.nombre_subcategoria = nombre_subcategoria;
    }

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }
}
