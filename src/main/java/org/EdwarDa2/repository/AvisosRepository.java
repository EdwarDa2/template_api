package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Aviso;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AvisosRepository {
    public List<Aviso> findAll() throws SQLException {
        List<Aviso> avisos = new ArrayList<>();
        String query = "SELECT * FROM avisos";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Aviso a = new Aviso();
                a.setId_aviso(rs.getInt("id_aviso"));
                a.setId_usuario(rs.getInt("id_usuario"));
                a.setFecha(rs.getDate("fecha").toLocalDate());
                a.setContenido(rs.getString("contenido"));
                avisos.add(a);
            }
        }
        return avisos;
    }

    public Aviso findById_aviso(int id_aviso) throws SQLException {
        Aviso aviso = null;
        String query = "SELECT * FROM avisos WHERE id_aviso = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_aviso);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aviso = new Aviso();
                    aviso.setId_aviso(rs.getInt("id_aviso"));
                    aviso.setId_usuario(rs.getInt("id_usuario"));
                    aviso.setFecha(rs.getDate("fecha").toLocalDate());
                    aviso.setContenido(rs.getString("contenido"));

                }
            }
        }

        return aviso;
    }
    public void save(Aviso aviso) throws SQLException {
        String query = "INSERT INTO avisos (id_usuario,fecha,contenido) VALUES (?,?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aviso.getId_usuario());
            stmt.setDate(2,Date.valueOf(aviso.getFecha()));
            stmt.setString(3,aviso.getContenido());
            stmt.executeUpdate();

        }
    }
    public void update(Aviso aviso) throws SQLException {
        String query = "UPDATE avisos  SET id_usuario = ?, fecha = ?,contenido = ? WHERE id_aviso = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, aviso.getId_usuario());
            stmt.setDate(2, Date.valueOf(aviso.getFecha()));
            stmt.setString(3, aviso.getContenido());
            stmt.setInt(4, aviso.getId_aviso());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_aviso) throws SQLException {
        String query = "DELETE FROM avisos WHERE id_aviso = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_aviso);
            stmt.executeUpdate();
        }
    }
}
