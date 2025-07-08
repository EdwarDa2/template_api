package org.alilopez.repository;

import org.alilopez.config.DatabaseConfig;
import org.alilopez.model.Mesero;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MeseroRepository {
    public List<Mesero> findAll() throws SQLException {
        List<Mesero> mesero = new ArrayList<>();
        String query = "SELECT * FROM meseros";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Mesero m = new Mesero();
                m.setId_mesero(rs.getInt("id_mesero"));
                m.setId_usuario(rs.getInt("id_usuario"));
                m.setClave(rs.getString("clave"));
                mesero.add(m);
            }
        }
        return mesero;
    }

    public Mesero findById_mesero(int id_mesero) throws SQLException {
        Mesero mesero = null;
        String query = "SELECT * FROM meseros WHERE id_mesero = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_mesero);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    mesero = new Mesero();
                    mesero.setId_mesero(rs.getInt("id_mesero"));
                    mesero.setId_usuario(rs.getInt("id_usuario"));
                    mesero.setClave(rs.getString("Clave"));
                }
            }
        }
        return mesero;
    }
    public void save(Mesero mesero) throws SQLException {

        String query = "INSERT INTO meseros (id_usuario, clave) VALUES (?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mesero.getId_usuario());
            stmt.setString(2, mesero.getClave());
            stmt.executeUpdate();
        }
    }
    public void update(Mesero mesero) throws SQLException {
        String query = "UPDATE meseros  SET id_usuario = ?, clave = ? WHERE id_mesero = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mesero.getId_usuario());
            stmt.setString(2, mesero.getClave());
            stmt.setInt(3, mesero.getId_usuario());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_mesero) throws SQLException {
        String query = "DELETE FROM meseros WHERE id_mesero = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_mesero);
            stmt.executeUpdate();
        }
    }
}
