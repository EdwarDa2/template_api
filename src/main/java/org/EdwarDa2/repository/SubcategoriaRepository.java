package org.EdwarDa2.repository;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Subcategoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class SubcategoriaRepository {


    public List<Subcategoria> findAll() throws SQLException {
        List<Subcategoria> subcategorias = new ArrayList<>();
        String query = "SELECT * FROM subcategorias";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Subcategoria c = new Subcategoria();
                c.setId_subcategoria(rs.getInt("id_subcategoria"));
                c.setId_categoria(rs.getInt("id_categoria"));
                c.setNombre_subcategoria(rs.getString("nombre_subcategoria"));
                subcategorias.add(c);
            }
        }
        return subcategorias;
    }

    public Subcategoria findById_subcategoria(int id_subcategoria) throws SQLException {
        Subcategoria subcategoria = null;
        String query = "SELECT * FROM subcategorias WHERE id_subcategoria = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_subcategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    subcategoria = new Subcategoria();
                    subcategoria.setId_subcategoria(rs.getInt("id_subcategoria"));
                    subcategoria.setId_categoria(rs.getInt("id_categoria"));
                    subcategoria.setNombre_subcategoria(rs.getString("nombre_subCategoria"));
                }
            }
        }

        return subcategoria;
    }

    public void save(Subcategoria subcategoria) throws SQLException {
        String query = "INSERT INTO subcategorias (id_categoria,nombre_subcategoria) VALUES (?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subcategoria.getId_categoria());
            stmt.setString(2,subcategoria.getNombre_subcategoria());
            stmt.executeUpdate();

        }
    }
    public void update(Subcategoria subcategoria) throws SQLException {
        String query = "UPDATE subcategorias  SET  id_categoria = ?, nombre_subcategoria = ? WHERE id_subcategoria  = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, subcategoria.getId_categoria());
            stmt.setString(2, subcategoria.getNombre_subcategoria());
            stmt.setInt(3, subcategoria.getId_subcategoria());
            stmt.executeUpdate();
        }
    }

    public void delete(int id_subcategoria) throws SQLException {
        String query = "DELETE FROM subcategorias WHERE id_subcategoria = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_subcategoria);
            stmt.executeUpdate();
        }
    }

}
