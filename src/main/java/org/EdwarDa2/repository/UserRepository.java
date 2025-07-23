package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    // Obtener todos los usuarios
    public List<User> findAll() throws SQLException {
        List<User> usuarios = new ArrayList<>();
        String query = "SELECT nombre, apellido_p, apellido_m, rol FROM usuario"; // Consultamos solo los campos necesarios

        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setNombre(rs.getString("nombre"));
                u.setApellido_p(rs.getString("apellido_p"));
                u.setApellido_m(rs.getString("apellido_m"));
                u.setRol(rs.getInt("rol")); // El rol debe ser un booleano
                usuarios.add(u);
            }
        }
        return usuarios;
    }


    // Obtener un usuario por ID
    public User findByIdUser(int idUser) throws SQLException {
        User user = null;
        String query = "SELECT id_usuario, nombre, apellido_p, apellido_m, clave, rol FROM usuario WHERE id_usuario = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setNombre(rs.getString("nombre"));
                    user.setApellido_p(rs.getString("apellido_p"));
                    user.setApellido_m(rs.getString("apellido_m"));
                    user.setRol(rs.getInt("rol"));
                }
            }
        }
        return user;
    }

    // Guardar un nuevo usuario
    public int save(User user) throws SQLException {
        String query = "INSERT INTO usuarios (nombre, apellido_p, apellido_m, rol) VALUES (?,?,?,?)";
        int idUser = 0;
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido_p());
            stmt.setString(3, user.getApellido_m()); // Asegurándonos de que se almacene correctamente
            stmt.setInt(4, user.getRol());
            stmt.executeUpdate();

            int rowsInserted = stmt.executeUpdate();

            if (rowsInserted > 0) {
                try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                    if (generatedKeys.next()) {
                        idUser = generatedKeys.getInt(1);
                        System.out.println("ID generado: " + idUser);
                    }
                }
            }
        }
        return idUser;
    }

    // Actualizar un usuario existente
    public void update(User user) throws SQLException {
        String query = "UPDATE usuario SET nombre = ?, apellido_p = ?, apellido_m = ?, rol = ? WHERE id_usuario = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellido_p());
            stmt.setString(3, user.getApellido_m());
            stmt.setInt(5, user.getRol());
            stmt.executeUpdate();
        }
    }

    // Eliminar un usuario
    public void delete(int idUser) throws SQLException {
        String query = "DELETE FROM usuario WHERE id_usuario = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, idUser);
            stmt.executeUpdate();
        }
    }
}
