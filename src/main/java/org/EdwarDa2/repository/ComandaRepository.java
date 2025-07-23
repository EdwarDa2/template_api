package org.EdwarDa2.repository;
import org.EdwarDa2.DTO.comandas.ComandaRequestDTO;
import org.EdwarDa2.DTO.comandas.DetalleComandaDTO;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Comanda;
import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class ComandaRepository {
    public List<ComandaRequestDTO> findAll() throws SQLException {
        List<ComandaRequestDTO> lista = new ArrayList<>();

        String comandaQuery ="SELECT * FROM comandas";
        String detalleQuery ="SELECT d.id_comanda, d.id_producto, d.cantidad, d.id_detallecomanda,d.comentario,p.nombre \n" +
                "\t FROM detallecomanda d \n" +
                "\t JOIN productos p  ON d.id_producto = p.id_producto;";

        Map<Integer, ArrayList<DetalleComandaDTO>> productosPorComanda = new HashMap<>();

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {


            try (PreparedStatement detalleStmt = conn.prepareStatement(detalleQuery);
                 ResultSet drs = detalleStmt.executeQuery()) {
                while (drs.next()) {
                    int idComanda = drs.getInt("id_comanda");
                    DetalleComandaDTO detalle = new DetalleComandaDTO();
                    detalle.setId_comanda(drs.getInt("id_comanda"));
                    detalle.setId_producto(drs.getInt("id_producto"));
                    detalle.setCantidad(drs.getInt("cantidad"));
                    detalle.setId_detalle(drs.getInt("id_detallecomanda"));
                    detalle.setNombreProducto(drs.getString("nombre"));
                    detalle.setComentario(drs.getString("comentario"));
                    productosPorComanda
                            .computeIfAbsent(idComanda, k -> new ArrayList<>())
                            .add(detalle);
                }
            }

            try (PreparedStatement comandaStmt = conn.prepareStatement(comandaQuery);
                 ResultSet rs = comandaStmt.executeQuery()) {

                while (rs.next()) {
                    int id_comanda = rs.getInt("id_comanda");
                    int id_mesa = rs.getInt("id_mesa");
                    int id_mesero = rs.getInt("id_mesero");
                    Timestamp fecha = Timestamp.valueOf(rs.getTimestamp("fecha_hora").toLocalDateTime());

                    ArrayList<DetalleComandaDTO> productos = productosPorComanda.getOrDefault(id_comanda, new ArrayList<>());

                    ComandaRequestDTO dto = new ComandaRequestDTO(
                            id_comanda,
                            id_mesa,
                            id_mesero,
                            fecha.toLocalDateTime(),
                            productos
                    );

                    lista.add(dto);
                }
            }
        }

        return lista;
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


                    }
                }
            }

            return comanda;
        }
    public void save( ComandaRequestDTO comanda) throws SQLException {
        String insertComanda = "INSERT INTO comandas (id_mesa, id_mesero, fecha_hora) VALUES (?, ?, ?)";
        String insertDetalle = "INSERT INTO detallecomanda (id_comanda, id_producto, cantidad,comentario) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false);
            int idGenerado;

            try (PreparedStatement stmt = conn.prepareStatement(insertComanda, Statement.RETURN_GENERATED_KEYS)) {
                stmt.setInt(1, comanda.getId_mesa());
                stmt.setInt(2, comanda.getId_mesero());
                LocalDateTime fecha = comanda.getFecha_hora() != null ? comanda.getFecha_hora() : LocalDateTime.now();
                stmt.setTimestamp(3, Timestamp.valueOf(fecha));
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
                    detalleStmt.setInt(1, idGenerado);
                    detalleStmt.setInt(2, producto.getId_producto());
                    detalleStmt.setInt(3, producto.getCantidad());
                    detalleStmt.setString(4, producto.getComentario());
                    detalleStmt.addBatch();
                }
                detalleStmt.executeBatch();
            }
            conn.commit();
        }catch (SQLException e) {
            e.printStackTrace();
            throw e;
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











