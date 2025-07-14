package org.EdwarDa2.repository;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Categoria;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
public class CategoriaRepository {


        public List<Categoria> findAll() throws SQLException {
            List<Categoria> categorias = new ArrayList<>();
            String query = "SELECT * FROM categorias";
            try (
                    Connection conn = DatabaseConfig.getDataSource().getConnection();
                    PreparedStatement stmt = conn.prepareStatement(query);
                    ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Categoria c = new Categoria();
                    c.setId_categoria(rs.getInt("id_categoria"));
                    c.setNombre_categoria(rs.getString("nombre_categoria"));
                    c.setId_subCategoria(rs.getInt("id_subCategoria"));
                    categorias.add(c);
                }
            }
            return categorias;
        }

        public Categoria findById_categoria(int id_categoria) throws SQLException {
            Categoria categoria = null;
            String query = "SELECT * FROM categorias WHERE id_categoria = ?";

            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {

                stmt.setInt(1, id_categoria);

                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        categoria = new Categoria();
                        categoria.setId_categoria(rs.getInt("id_categoria"));
                        categoria.setNombre_categoria(rs.getString("nombre_categoria"));
                        categoria.setId_subCategoria(rs.getInt("id_subCategoria"));
                    }
                }
            }

            return categoria;
        }

        public void save(Categoria categoria) throws SQLException {
            String query = "INSERT INTO categorias (nombre_categoria,id_subCategoria) VALUES (?,?)";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, categoria.getNombre_categoria());
                stmt.setInt(2,categoria.getId_subCategoria());
                stmt.executeUpdate();

            }
        }
        public void update(Categoria categoria) throws SQLException {
            String query = "UPDATE categorias  SET  nombre_categoria = ?, id_subCategoria = ? WHERE id_categoria  = ?";
            try (Connection conn = DatabaseConfig.getDataSource().getConnection();
                 PreparedStatement stmt = conn.prepareStatement(query)) {
                stmt.setString(1, categoria.getNombre_categoria());
                stmt.setInt(2, categoria.getId_subCategoria());
                stmt.setInt(3, categoria.getId_categoria());
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













}
