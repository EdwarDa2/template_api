package org.alilopez.repository;
import org.alilopez.config.DatabaseConfig;
import org.alilopez.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class UserRepository {

    public List<User> findAll() throws SQLException {
        List<User> usuario = new ArrayList<>();
        String query = "SELECT * FROM usuario";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                User u = new User();
                u.setId_usuario(rs.getInt("id_usuario"));
                u.setNombre(rs.getString("nombre"));
                u.setApellidoP(rs.getString("apellidoP"));
                u.setEmail(rs.getString("email"));
                u.setContrasena(rs.getString("contrasena"));
                u.setRol(rs.getBoolean("rol"));
                usuario.add(u);
            }
        }
        return usuario;
    }

    public User findByIdUser(int idUser) throws SQLException {
        User user = null;
        String query = "SELECT * FROM usuario WHERE id_usuario = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, idUser);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    user = new User();
                    user.setId_usuario(rs.getInt("id_usuario"));
                    user.setNombre(rs.getString("nombre"));
                    user.setApellidoP(rs.getString("apellidoP"));
                    user.setEmail(rs.getString("email"));
                    user.setContrasena(rs.getString("contrasena"));
                    user.setRol(true);


                }
            }
        }
        return user;
    }
    public void save(User user) throws SQLException {

        String query = "INSERT INTO usuario (nombre, apellidoP, email,contrasena,rol) VALUES (?,?,?,?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, user.getNombre());
            stmt.setString(2, user.getApellidoP());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getContrasena());
            stmt.setBoolean(5,user.isRol());
            stmt.executeUpdate();
        }
    }
        public void update(User user) throws SQLException {
            String query = "UPDATE usuario  SET nombre = ?, apellidoP = ?,email = ?,contrasena = ?,rol = ? WHERE id_usuario = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, user.getNombre());
                stmt.setString(2, user.getApellidoP());
                stmt.setString(3, user.getEmail());
                stmt.setString(4, user.getContrasena());
                stmt.setBoolean(5,user.isRol());
                stmt.setInt(6, user.getId_usuario());
                stmt.executeUpdate();
            }
        }
        public void delete(int idUser) throws SQLException {
            String query = "DELETE FROM usuario WHERE id_usuario = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, idUser);
                stmt.executeUpdate();
            }
        }
}
