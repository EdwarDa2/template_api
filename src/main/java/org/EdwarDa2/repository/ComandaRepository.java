package org.EdwarDa2.repository;
import org.EdwarDa2.DTO.comandas.ComandaRequestDTO;
import org.EdwarDa2.DTO.comandas.DetalleComandaDTO;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Comanda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;




public class ComandaRepository {
        public List<ComandaRequestDTO> allProducts() throws SQLException {
            List<ComandaRequestDTO> listaProductos = new ArrayList<>();
            String query = "SELECT * FROM comandas";
            try (
                    Connection conn = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    ComandaRequestDTO d = new ComandaRequestDTO();
                    d.setId_comanda(rs.getInt("id_comanda"));
                    d.setId_mesa(rs.getInt("id_mesa"));
                    d.setId_mesero(rs.getInt("id_mesero"));
                    d.setFecha_hora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                    d.setId_detalleComanda(rs.getInt("id_detalleComanda"));
                    listaProductos.add(d);
                }
            }
            return listaProductos;
        }


        public Comanda findById_comanda(int id_comanda) throws SQLException {
            Comanda comanda = null;
            String query = "SELECT * FROM comandas WHERE id_comanda = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, id_comanda);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        comanda = new Comanda();
                        comanda.setId_comanda(rs.getInt("id_comanda"));
                        comanda.setId_mesa(rs.getInt("id_mesa"));
                        comanda.setId_mesero(rs.getInt("id_mesero"));
                        comanda.setFecha_hora(rs.getTimestamp("fecha_hora").toLocalDateTime());
                        comanda.setId_detalleComanda(rs.getInt("id_detalleComanda"));

                    }
                }
            }

            return comanda;
        }
    public void save( ComandaRequestDTO comanda) throws SQLException {
        String insertComanda = "INSERT INTO comandas (id_mesa, id_mesero, fecha_hora,id_detalleComanda) VALUES (?, ?, ?, ?)";
        String insertDetalle = "INSERT INTO detalleComanda (id_comanda, id_producto, cantidad) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            int idGenerado;

            try (PreparedStatement stmt = conn.prepareStatement(insertComanda, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, comanda.getId_mesa());
                stmt.setInt(2, comanda.getId_mesero());
                stmt.setTimestamp(3, Timestamp.valueOf(comanda.getFecha_hora()));
                stmt.setInt(4,comanda.getId_detalleComanda());
                stmt.executeUpdate();

                ResultSet rs = stmt.getGeneratedKeys();
                if (rs.next()) {
                    idGenerado = rs.getInt(1);
                } else {
                    conn.rollback();
                    throw new SQLException("No se generó ID de comanda.");
                }
            }

            try (PreparedStatement detalleStmt = conn.prepareStatement(insertDetalle)) {
                for (DetalleComandaDTO producto : comanda.getListaProductos()) {
                    detalleStmt.setInt(1, producto.getId_comanda());
                    detalleStmt.setInt(2, producto.getId_producto());
                    detalleStmt.setInt(3, producto.getCantidad());
                    detalleStmt.addBatch();
                }
                detalleStmt.executeBatch();
            }


            conn.commit();
        }
    }
        public void update(Comanda comanda) throws SQLException {
            String query = "UPDATE comandas  SET id_mesa = ?, id_mesero = ?, fecha_hora = ?,id_detalleComanda = ? WHERE id_comanda = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, comanda.getId_mesa());
                stmt.setInt(2, comanda.getId_mesero());
                stmt.setTimestamp(3, Timestamp.valueOf(comanda.getFecha_hora()));
                stmt.setInt(4, comanda.getId_detalleComanda());
                stmt.setInt(5, comanda.getId_comanda());
                stmt.executeUpdate();
            }
        }
        public void delete(int id_comanda) throws SQLException {
            String query = "DELETE FROM comandas WHERE id_comanda = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_comanda);
                stmt.executeUpdate();
            }
        }
    }











