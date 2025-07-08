package org.EdwarDa2.model;

public class Categoria {
    private int id_categoria;
    private String nombre_categoria;
    private int id_subCategoria;

    public int getId_categoria() {
        return id_categoria;
    }

    public void setId_categoria(int id_categoria) {
        this.id_categoria = id_categoria;
    }

    public String getNombre_categoria() {
        return nombre_categoria;
    }

    public void setNombre_categoria(String nombre_categoria) {
        this.nombre_categoria = nombre_categoria;
    }

    public int getId_subCategoria() {
        return id_subCategoria;
    }

    public void setId_subCategoria(int id_subCategoria) {
        this.id_subCategoria = id_subCategoria;
    }
}
