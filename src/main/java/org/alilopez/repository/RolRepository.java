package org.alilopez.repository;

import org.alilopez.config.DatabaseConfig;
import org.alilopez.model.Rol;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class RolRepository {
    public List<Rol> findAll() throws SQLException {
        List<Rol> rols = new ArrayList<>();
        String query = "SELECT * FROM rol";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Rol r = new Rol();
                r.setId_rol(rs.getInt("id_rol"));
                r.setType_rol(rs.getBoolean("type_rol"));
                rols.add(r);
            }
        }
        return rols;
    }

    public Rol findById_rol(int id_rol) throws SQLException {
        Rol rol = null;
        String query = "SELECT * FROM rol WHERE id_rol = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_rol);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    rol = new Rol();
                    rol.setId_rol(rs.getInt("id_rol"));
                    rol.setType_rol(rs.getBoolean("type_rol"));


                }
            }
        }

        return rol;
    }

    public void save(Rol rol) throws SQLException {
        String query = "INSERT INTO rol (type_rol) VALUES (?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, rol.isType_rol());
            stmt.executeUpdate();

        }
    }
    public void update(Rol rol) throws SQLException {
        String query = "UPDATE rol  SET type_rol = ? WHERE id_rol = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setBoolean(1, rol.isType_rol());
            stmt.setInt(2, rol.getId_rol());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_rol) throws SQLException {
        String query = "DELETE FROM rol WHERE id_rol = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_rol);
            stmt.executeUpdate();
        }
    }
}
