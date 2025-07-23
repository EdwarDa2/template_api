package org.EdwarDa2.repository;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Categoria;
import org.EdwarDa2.model.Subcategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CategoriaRepository {

    public List<Categoria> getAllCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String query = "SELECT id_categoria, nombre_categoria FROM categorias";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {

                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categorias;
    }
    public List<Subcategoria> getSubcategoriasByCategoria(int categoriaId) {
        List<Subcategoria> subcategorias = new ArrayList<>();
        String query = "SELECT * FROM subcategorias WHERE id_categoria = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, categoriaId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Subcategoria subcategoria = new Subcategoria(
                        rs.getInt("id_subcategoria"),
                        rs.getString("nombre_subcategoria"),
                        rs.getInt("id_categoria")
                );
                subcategorias.add(subcategoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return subcategorias;
    }

    public void save(Categoria categoria) throws SQLException {
        // Solo se necesita un placeholder para nombre_categoria
        String query = "INSERT INTO categorias (nombre_categoria) VALUES (?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, categoria.getNombre_categoria());
            stmt.executeUpdate();
        }
    }
        public void update(Categoria categoria) throws SQLException {
            String query = "UPDATE categorias  SET  nombre_categoria = ? WHERE id_categoria  = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, categoria.getNombre_categoria());
                stmt.setInt(2, categoria.getId_categoria());
                stmt.executeUpdate();
            }
        }

        public void delete(int id_categoria) throws SQLException {
            String query = "DELETE FROM categorias WHERE id_categoria = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setInt(1, id_categoria);
                stmt.executeUpdate();
            }
        }
    public Categoria findById(int id) throws SQLException {
        String query = "SELECT * FROM categorias WHERE id_categoria = ?";
        Categoria categoria = null;
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                categoria.setNombre_categoria(rs.getString("nombre_categoria"));
            }
        }
        return categoria;
    }














}
