package org.EdwarDa2.DTO.comandas;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class ComandaRequestDTO {
    private int id_comanda;
    private int id_mesa;
    private int id_mesero;
    private LocalDateTime fecha_hora;
    private int id_detalleComanda;
    private ArrayList<DetalleComandaDTO> listaProductos;
    public ComandaRequestDTO() {
        this.id_comanda = id_comanda;
        this.id_mesa = id_mesa;
        this.id_mesero = id_mesero;
        this.fecha_hora = fecha_hora;
        this.id_detalleComanda = id_detalleComanda;
        this.listaProductos = listaProductos;
    }

    public int getId_mesa() {
        return id_mesa;
    }

    public void setId_mesa(int id_mesa) {
        this.id_mesa = id_mesa;
    }

    public int getId_mesero() {
        return id_mesero;
    }

    public void setId_mesero(int id_mesero) {
        this.id_mesero = id_mesero;
    }

    public List<DetalleComandaDTO> getListaProductos() {
        return listaProductos;
    }

    public void setListaProductos(ArrayList<DetalleComandaDTO> listaProductos) {
        this.listaProductos = listaProductos;
    }
    public LocalDateTime getFecha_hora() {
        return fecha_hora;
    }
    public void setFecha_hora(LocalDateTime fecha_hora) {
        this.fecha_hora = fecha_hora;
    }

    public int getId_detalleComanda() {
        return id_detalleComanda;
    }

    public void setId_detalleComanda(int id_detalleComanda) {
        this.id_detalleComanda = id_detalleComanda;
    }

    public int getId_comanda() {return id_comanda;}

    public void setId_comanda(int id_comanda) {this.id_comanda = id_comanda;}
}
