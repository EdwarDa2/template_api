package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Categoria;
import org.EdwarDa2.model.Producto;
import org.EdwarDa2.model.Subcategoria;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        // Recuperamos todos los productos. Asegúrese de que los nombres de columna coincidan con los de la base de datos.
        String query = "SELECT * FROM productos";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Producto p = new Producto();
                p.setId_producto(rs.getInt("id_producto"));
                p.setNombre(rs.getString("nombre"));
                p.setPrecio(rs.getFloat("precio"));
                // Usar el nombre correcto de la columna para ID de subcategoría
                // Dependiendo de la base de datos, puede ser "id_subcategoria". Ajustar según el esquema real.
                try {
                    p.setSubcategoriaId(rs.getInt("id_subcategoria"));
                } catch (SQLException e) {
                    // En caso de que la columna se llame "subcategoriaid" (sin guion bajo)
                    p.setSubcategoriaId(rs.getInt("subcategoriaid"));
                }
                productos.add(p);
            }
        }
        return productos;
    }

    public List<Producto> findBySubcategoria(int id_subcategoria) throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT * FROM productos WHERE id_subcategoria = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_subcategoria);

            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Producto producto = new Producto();
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getFloat("precio"));
                    producto.setSubcategoriaId(rs.getInt("id_subcategoria"));
                    productos.add(producto);
                }
            }
        }

        return productos;
    }


    public void save(Producto producto) throws SQLException {
        // Inserta un nuevo producto. Utiliza el nombre correcto de la columna para el ID de subcategoría.
        String query = "INSERT INTO productos (nombre, precio, id_subcategoria) VALUES (?,?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getSubcategoriaId());
            stmt.executeUpdate();
        }
    }
    public void update(Producto producto) throws SQLException {
        // Actualizar un producto. Asegúrate de usar el nombre correcto para la columna de subcategoría.
        String query = "UPDATE productos SET nombre = ?, precio = ?, id_subcategoria = ? WHERE id_producto = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getSubcategoriaId());
            stmt.setInt(4, producto.getId_producto());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_producto) throws SQLException {
        String sql = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, id_producto);
            stmt.executeUpdate();
        }
    }
    public Producto savePro(Producto producto) throws SQLException {
        String query = "INSERT INTO productos ( id_subcategoria, nombre, precio) VALUES ( ?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {

            stmt.setInt(1, producto.getSubcategoriaId());
            stmt.setString(2, producto.getNombre());
            stmt.setDouble(3, producto.getPrecio());


            stmt.executeUpdate();
            try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    producto.setId_producto(generatedKeys.getInt(1));
                }
            }
            return producto;
        }
    }

    public List<Producto> AllPro() throws SQLException {
        List<Producto> productos = new ArrayList<>();
        String query = "SELECT p.id_producto, p.nombre, p.precio, p.id_subcategoria, s.id_categoria " +
                "FROM productos p " +
                "JOIN subcategorias s ON p.id_subcategoria = s.id_subcategoria";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                Producto producto = new Producto();
                producto.setId_producto(rs.getInt("id_producto"));
                producto.setNombre(rs.getString("nombre"));
                producto.setPrecio(rs.getFloat("precio"));

                Subcategoria subcategoria = new Subcategoria();
                subcategoria.setId_subcategoria(rs.getInt("id_subcategoria"));

                Categoria categoria = new Categoria();
                categoria.setId_categoria(rs.getInt("id_categoria"));
                subcategoria.setCategoria(categoria);
                producto.setSubcategoria(subcategoria);

                productos.add(producto);
            }
        }
        return productos;
    }
}
