
package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Mesa;

import java.sql.*;
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
                m.setId_mesero(rs.getInt("id_mesero"));
                m.setId_cuenta(rs.getObject("id_cuenta") != null ? rs.getInt("id_cuenta") : null);
                m.setNum_personas(rs.getInt("num_personas"));
                m.setNum_mesa(rs.getInt("num_mesa"));
                m.setStatus(rs.getBoolean("status"));
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
                    mesa.setId_mesero(rs.getInt("id_mesero"));
                    mesa.setId_cuenta(rs.getObject("id_cuenta") != null ? rs.getInt("id_cuenta") : null);
                    mesa.setNum_personas(rs.getInt("num_personas"));
                    mesa.setNum_mesa(rs.getInt("num_mesa"));
                    mesa.setStatus(rs.getBoolean("status"));
                }
            }
        }
        return mesa;
    }

    public void save(Mesa mesa) throws SQLException {
        String query = "INSERT INTO mesas (id_mesero, id_cuenta, num_personas, num_mesa, status) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, mesa.getId_mesero());
            if (mesa.getId_cuenta() != null) {
                stmt.setInt(2, mesa.getId_cuenta());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, mesa.getNum_personas());
            stmt.setInt(4, mesa.getNum_mesa());
            stmt.setBoolean(5, mesa.isStatus());

            stmt.executeUpdate();
        }
    }

    public void update(Mesa mesa) throws SQLException {
        String query = "UPDATE mesas SET id_mesero = ?, id_cuenta = ?, num_personas = ?, num_mesa = ?, status = ? WHERE id_mesa = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, mesa.getId_mesero());
            if (mesa.getId_cuenta() != null) {
                stmt.setInt(2, mesa.getId_cuenta());
            } else {
                stmt.setNull(2, Types.INTEGER);
            }
            stmt.setInt(3, mesa.getNum_personas());
            stmt.setInt(4, mesa.getNum_mesa());
            stmt.setBoolean(5, mesa.isStatus());
            stmt.setInt(6, mesa.getId_mesa());

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
