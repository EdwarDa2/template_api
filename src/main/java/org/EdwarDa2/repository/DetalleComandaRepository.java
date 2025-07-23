package org.EdwarDa2.repository;

import org.EdwarDa2.DTO.comandas.DetalleComandaDTO;
import org.EdwarDa2.config.DatabaseConfig;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class DetalleComandaRepository {
    public List<DetalleComandaDTO> allProducts() throws SQLException {
        List<DetalleComandaDTO> listaProductos = new ArrayList<>();
        String query = "SELECT * FROM comandas";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                DetalleComandaDTO l = new DetalleComandaDTO();
                l.setId_comanda(rs.getInt("id_comanda"));
                l.setId_producto(rs.getInt("id_producto"));
                l.setCantidad(rs.getInt("cantidad"));
                l.setComentario(rs.getString("comentario"));
                listaProductos.add(l);
            }
        }
        return listaProductos;
    }
}
