package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Subcategoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SubcategoriaRepository {

    public List<Subcategoria> findAll() throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM subcategorias";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                lista.add(new Subcategoria(
                        rs.getInt("id_subcategoria"),
                        rs.getString("nombre_subcategoria"),
                        rs.getInt("id_categoria")
                ));
            }
        }
        return lista;
    }

    public Subcategoria findById(int id) throws SQLException {
        String query = "SELECT * FROM subcategorias WHERE id_subcategoria = ?";
        Subcategoria subcategoria = null;
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                subcategoria = new Subcategoria();
                subcategoria.setId_subcategoria(rs.getInt("id_subcategoria"));
                subcategoria.setNombre_subcategoria(rs.getString("nombre_subcategoria"));
            }
        }
        return subcategoria;
    }

    public List<Subcategoria> findByCategoria(int id_categoria) throws SQLException {
        List<Subcategoria> lista = new ArrayList<>();
        String sql = "SELECT * FROM subcategorias WHERE id_categoria = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id_categoria);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                lista.add(new Subcategoria(
                        rs.getInt("id_subcategoria"),
                        rs.getString("nombre_subcategoria"),
                        rs.getInt("id_categoria")
                ));
            }
        }
        return lista;
    }

    public void save(Subcategoria sub) throws SQLException {
        String sql = "INSERT INTO subcategorias (nombre_subcategoria, id_categoria) VALUES (?, ?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getNombre_subcategoria());
            stmt.setInt(2, sub.getId_categoria());
            stmt.executeUpdate();
        }
    }

    public void update(Subcategoria sub) throws SQLException {
        String sql = "UPDATE subcategorias SET nombre_subcategoria = ?, id_categoria = ? WHERE id_subcategoria = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, sub.getNombre_subcategoria());
            stmt.setInt(2, sub.getId_categoria());
            stmt.setInt(3, sub.getId_subcategoria());
            stmt.executeUpdate();
        }
    }

    public void delete(int id) throws SQLException {
        String sql = "DELETE FROM subcategorias WHERE id_subcategoria = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }
}
