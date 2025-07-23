package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Aviso;
import java.time.LocalDateTime;

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
                ResultSet rs = stmt.executeQuery()
        ) {
            while (rs.next()) {
                Aviso a = new Aviso();
                a.setId_aviso(rs.getInt("id_aviso"));
                a.setId_admin(rs.getInt("id_admin"));
                a.setFecha(rs.getTimestamp("fecha").toLocalDateTime()); // CAMBIO
                a.setContenido(rs.getString("contenido"));
                avisos.add(a);
            }
        }

        return avisos;
    }

    public Aviso findById_aviso(int id_aviso) throws SQLException {
        Aviso aviso = null;
        // Consulta ahora por id_aviso (antes se usaba id_admin incorrectamente)
        String query = "SELECT * FROM avisos WHERE id_aviso = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_aviso);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    aviso = new Aviso();
                    aviso.setId_aviso(rs.getInt("id_aviso"));
                    aviso.setId_admin(rs.getInt("id_admin"));
                    aviso.setFecha(rs.getTimestamp("fecha").toLocalDateTime());
                    aviso.setContenido(rs.getString("contenido"));
                }
            }
        }
        return aviso;
    }

    public int insert(Aviso aviso) throws SQLException {
        int idGenerado = -1;
        String query = "INSERT INTO avisos (id_admin, fecha, contenido) VALUES (?, NOW(), ?)"; // fecha automática

        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)
        ) {
            stmt.setInt(1, aviso.getId_admin());
            stmt.setString(2, aviso.getContenido());
            stmt.executeUpdate();

            ResultSet rs = stmt.getGeneratedKeys();
            if (rs.next()) {
                idGenerado = rs.getInt(1);
            }
        }

        return idGenerado;
    }

    public void update(Aviso aviso) throws SQLException {
        String query = "UPDATE avisos SET id_admin = ?, fecha = ?, contenido = ? WHERE id_aviso = ?";

        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, aviso.getId_admin());
            stmt.setTimestamp(2, Timestamp.valueOf(aviso.getFecha())); // CAMBIO
            stmt.setString(3, aviso.getContenido());
            stmt.setInt(4, aviso.getId_aviso());
            stmt.executeUpdate();
        }
    }

    public void delete(int id_aviso) throws SQLException {
        String query = "DELETE FROM avisos WHERE id_aviso = ?";

        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query)
        ) {
            stmt.setInt(1, id_aviso);
            stmt.executeUpdate();
        }
    }
}