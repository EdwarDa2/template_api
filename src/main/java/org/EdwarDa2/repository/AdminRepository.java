package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Admin;
import org.EdwarDa2.model.Usuario;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class AdminRepository {

    public List<Admin> findAll() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM admins";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Admin a = new Admin();
                a.setId_admin(rs.getInt("id_admin"));
                a.setId_usuario(rs.getInt("id_usuario"));
                admins.add(a);
            }
        }
        return admins;
    }

    public Admin findById_admin(int id_admin) throws SQLException {
        Admin admin = null;
        String query = "SELECT * FROM admins WHERE id_admin = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_admin);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    admin = new Admin();
                    admin.setId_admin(rs.getInt("id_admin"));
                    admin.setId_usuario(rs.getInt("id_usuario"));
                }
            }
        }

        return admin;
    }

    public void save(Admin admin) throws SQLException {
        String query = "INSERT INTO admins (id_usuario, clave) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admin.getId_usuario());
            stmt.setString(2, admin.getClave());
            stmt.executeUpdate();
        }
    }

    public void update(Admin admin) throws SQLException {
        String query = "UPDATE admins SET id_usuario = ? WHERE id_admin = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, admin.getId_usuario());
            stmt.setInt(2, admin.getId_admin());
            stmt.executeUpdate();
        }
    }

    public void delete(int id_admin) throws SQLException {
        String query = "DELETE FROM admins WHERE id_admin = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_admin);
            stmt.executeUpdate();
        }
    }

    public Usuario validarAdmin(String clave) {
        String query = "SELECT u.id_usuario, u.nombre, u.apellido_p, u.apellido_m, a.clave, 'Administrador' AS tipoRol " +
                "FROM admins a " +
                "JOIN usuarios u ON a.id_usuario = u.id_usuario " +
                "WHERE a.clave = ?";

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
