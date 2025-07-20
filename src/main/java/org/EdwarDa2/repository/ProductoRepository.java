package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Producto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductoRepository {
    public List<Producto> findAll() throws SQLException {
        List<Producto> productos = new ArrayList<>();
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
                p.setId_subCategoria(rs.getInt("id_subCategoria"));
                productos.add(p);
            }
        }
        return productos;
    }

    public Producto findById_producto(int id_producto) throws SQLException {
        Producto producto = null;
        String query = "SELECT * FROM productos WHERE id_producto = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_producto);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    producto = new Producto();
                    producto.setId_producto(rs.getInt("id_producto"));
                    producto.setNombre(rs.getString("nombre"));
                    producto.setPrecio(rs.getFloat("precio"));
                    producto.setId_subCategoria(rs.getInt("id_subCategoria"));
                }
            }
        }

        return producto;
    }

    public void save(Producto producto) throws SQLException {
        String query = "INSERT INTO productos (nombre,precio,id_SubCategoria) VALUES (?,?,?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getId_subCategoria());
            stmt.executeUpdate();

        }
    }
    public void update(Producto producto) throws SQLException {
        String query = "UPDATE productos  SET nombre = ?,precio = ?,id_subCategoria = ? WHERE id_producto = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, producto.getNombre());
            stmt.setFloat(2, producto.getPrecio());
            stmt.setInt(3, producto.getId_subCategoria());
            stmt.setInt(4, producto.getId_producto());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_producto) throws SQLException {
        String query = "DELETE FROM productos WHERE id_producto = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_producto);
            stmt.executeUpdate();
        }
    }
}



