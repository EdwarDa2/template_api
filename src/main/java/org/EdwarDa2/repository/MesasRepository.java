package org.EdwarDa2.repository;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Mesa;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MesasRepository {

        public List<Mesa> findAll() throws SQLException {
            List<Mesa> mesas = new ArrayList<>();
            String query = "SELECT * FROM mesas";
            try (
                    Connection conn = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Mesa m = new Mesa();
                    m.setId_mesa(rs.getInt("id_mesa"));
                    m.setNum_mesa(rs.getInt("num_mesa"));
                    m.setStatus(rs.getBoolean("status"));
                    m.setId_mesero(rs.getInt("id_mesero"));
                    m.setId_total(rs.getInt("id_total"));
                    mesas.add(m);
                }
            }
            return mesas;
        }

        public Mesa findById_mesa(int id_mesa) throws SQLException {
            Mesa mesa = null;
            String query = "SELECT * FROM mesas WHERE id_mesa = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, id_mesa);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        mesa = new Mesa();
                        mesa.setId_mesa(rs.getInt("id_mesa"));
                        mesa.setNum_mesa(rs.getInt("num_mesa"));
                        mesa.setStatus(rs.getBoolean("status"));
                        mesa.setId_mesero(rs.getInt("id_mesero"));
                        mesa.setId_total(rs.getInt("id_total"));

                    }
                }
            }

            return mesa;
        }

        public void save(Mesa mesa) throws SQLException {
            String query = "INSERT INTO mesas (num_mesa,status,id_mesero,id_total) VALUES (?,?,?,?)";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, mesa.getNum_mesa());
                stmt.setBoolean(2, mesa.isStatus());
                stmt.setInt(3, mesa.getId_mesero());
                stmt.setInt(4,mesa.getId_total());
                stmt.executeUpdate();

            }
        }
        public void update(Mesa mesa) throws SQLException {
            String query = "UPDATE mesas  SET num_mesa = ?, status = ?, id_mesero = ?,id_total = ? WHERE id_mesa = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, mesa.getNum_mesa());
                stmt.setBoolean(2, mesa.isStatus());
                stmt.setInt(3, mesa.getId_mesero());
                stmt.setInt(4, mesa.getId_total());
                stmt.setInt(5, mesa.getId_mesa());
                stmt.executeUpdate();
            }
        }
        public void delete(int id_mesa) throws SQLException {
            String query = "DELETE FROM mesas WHERE id_mesa = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_mesa);
                stmt.executeUpdate();
            }
        }
}









