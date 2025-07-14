package org.EdwarDa2.repository;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Comanda;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ComandaRepository {
        public List<Comanda> findAll() throws SQLException {
            List<Comanda> comandas = new ArrayList<>();
            String query = "SELECT * FROM comandas";
            try (
                    Connection conn = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Comanda c = new Comanda();
                    c.setId_comanda(rs.getInt("id_comanda"));
                    c.setId_mesa(rs.getInt("id_mesa"));
                    c.setId_mesero(rs.getInt("id_mesero"));
                    c.setHora(rs.getTime("hora").toLocalTime());
                    c.setId_producto(rs.getInt("id_producto"));
                    comandas.add(c);
                }
            }
            return comandas;
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
                        comanda.setHora(rs.getTime("hora").toLocalTime());
                        comanda.setId_producto(rs.getInt("id_producto"));

                    }
                }
            }

            return comanda;
        }
        public void save(Comanda comanda) throws SQLException {
            String query = "INSERT INTO comandas (id_mesa,id_mesero,hora,id_producto) VALUES (?,?,?,?)";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, comanda.getId_mesa());
                stmt.setInt(2, comanda.getId_mesero());
                stmt.setTime(3, Time.valueOf(comanda.getHora()));
                stmt.setInt(4,comanda.getId_producto());
                stmt.executeUpdate();

            }
        }
        public void update(Comanda comanda) throws SQLException {
            String query = "UPDATE comandas  SET id_mesa = ?, id_mesero = ?, hora = ?,id_producto = ? WHERE id_comanda = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, comanda.getId_mesa());
                stmt.setInt(2, comanda.getId_mesero());
                stmt.setTime(3, Time.valueOf(comanda.getHora()));
                stmt.setInt(4, comanda.getId_producto());
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











