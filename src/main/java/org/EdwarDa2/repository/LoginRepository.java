package org.EdwarDa2.repository;

import org.EdwarDa2.model.Usuario;
import org.EdwarDa2.config.DatabaseConfig;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class LoginRepository {

    // Validar el login de un administrador
    public Usuario validarAdmin(String clave) {
        String query = "SELECT u.id_usuario, u.nombre, u.apellido_p, u.apellido_m, a.clave, 'Administrador' AS tipoRol " +
                "FROM admins a " +
                "JOIN usuarios u ON a.id_usuario = u.id_usuario " +
                "WHERE a.clave = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clave);  // Se pasa la clave para la consulta
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si se encuentra al usuario, devolver los datos completos
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido_p"),
                        rs.getString("apellido_m"),
                        rs.getString("tipoRol"),
                        rs.getString("clave")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  // Si no se encuentra el usuario, retornar null
    }

    // Validar el login de un mesero
    public Usuario validarMesero(String clave) {
        String query = "SELECT u.id_usuario, u.nombre, u.apellido_p, u.apellido_m, m.clave, 'Mesero' AS tipoRol " +
                "FROM meseros m " +
                "JOIN usuarios u ON m.id_usuario = u.id_usuario " +
                "WHERE m.clave = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setString(1, clave);  // Se pasa la clave para la consulta
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // Si se encuentra al usuario, devolver los datos completos
                return new Usuario(
                        rs.getInt("id_usuario"),
                        rs.getString("nombre"),
                        rs.getString("apellido_p"),
                        rs.getString("apellido_m"),
                        rs.getString("tipoRol"),
                        rs.getString("clave")
                );
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        return null;  // Si no se encuentra el usuario, retornar null
    }
}
