package org.EdwarDa2.DTO;


import java.util.List;

public class StatsDTO {
    List<String> nombre;
    List<Float> precio;
    public StatsDTO(List<String> nombre, List<Float> precio) {
        this.nombre = nombre;
        this.precio = precio;
    }

    public List<String> getNombre() {
        return nombre;
    }

    public void setNombre(List<String> nombre) {
        this.nombre = nombre;
    }

    public List<Float> getPrecio() {
        return precio;
    }

    public void setPrecio(List<Float> precio) {
        this.precio = precio;
    }
}
