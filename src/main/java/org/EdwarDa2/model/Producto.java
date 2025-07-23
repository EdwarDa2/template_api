package org.EdwarDa2.model;

public class Producto {
    private int id_producto;
    private String nombre;
    private float precio;
    private int categoriaId;  // Este es el ID de la categoría
    private int subcategoriaId;  // Este es el ID de la subcategoría
    private Categoria categoria;
    private Subcategoria subcategoria;

    // Getters y setters para los campos
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

    public int getCategoriaId() {  // Usamos el ID de la categoría
        return categoriaId;
    }

    public void setCategoriaId(int categoriaId) {
        this.categoriaId = categoriaId;
    }

    public int getSubcategoriaId() {  // Usamos el ID de la subcategoría
        return subcategoriaId;
    }

    public void setSubcategoriaId(int subcategoriaId) {
        this.subcategoriaId = subcategoriaId;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

    public Subcategoria getSubcategoria() {
        return subcategoria;
    }

    public void setSubcategoria(Subcategoria subcategoria) {
        this.subcategoria = subcategoria;
    }
}
