package org.EdwarDa2.DTO.comandas;

public class DetalleComandaDTO {
    private int id_detalle;
    private int id_comanda;
    private int id_producto;
    private int cantidad;
    private String nombreProducto;
    private String comentario;


    public DetalleComandaDTO() {}

    public int getId_comanda() {
        return id_comanda;
    }

    public void setId_comanda(int id_comanda) {
        this.id_comanda = id_comanda;
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getId_detalle() {
        return id_detalle;
    }

    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }

    public String getNombreProducto() {return nombreProducto;}

    public void setNombreProducto(String nombreProducto) {this.nombreProducto = nombreProducto;}
    public String getComentario() {return comentario;}
    public void setComentario(String comentario) {this.comentario = comentario;}

}
