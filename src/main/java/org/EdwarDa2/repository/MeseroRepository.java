package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Mesero;
import org.EdwarDa2.model.Usuario;

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
                    // Usar siempre el nombre de columna en minúsculas para evitar confusiones
                    mesero.setClave(rs.getString("clave"));
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
        String query = "UPDATE meseros SET id_usuario = ?, clave = ? WHERE id_mesero = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, mesero.getId_usuario());
            stmt.setString(2, mesero.getClave());
            // El WHERE debe usar el id_mesero, no el id_usuario
            stmt.setInt(3, mesero.getId_mesero());
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

    public Usuario validarMesero(String clave) {
        String query = "SELECT u.id_usuario, u.nombre, u.apellido_p, u.apellido_m, m.clave, 'Mesero' AS tipoRol " +
                "FROM meseros m " +
                "JOIN usuarios u ON m.id_usuario = u.id_usuario " +
                "WHERE m.clave = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clave);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido_p"),
                        rs.getString("apellido_m"),
                        rs.getString("tipoRol"),
                        rs.getString("clave")
                );
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }
}
