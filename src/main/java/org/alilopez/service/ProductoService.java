package org.alilopez.service;
import org.alilopez.model.Producto;
import org.alilopez.repository.ProductoRepository;


import java.sql.SQLException;
import java.util.List;

public class ProductoService {
    private final ProductoRepository productoRepo;
    public ProductoService(ProductoRepository productoRepo) {
        this.productoRepo = productoRepo;
    }
    public List<Producto> getAllProductos() throws SQLException {
        return productoRepo.findAll();
    }

    public Producto getById_Producto(int id_producto) throws SQLException {
        return productoRepo.findById_producto(id_producto);
    }

    public void createProducto(Producto producto) throws SQLException {

        productoRepo.save(producto);
    }
    public void updateProducto(Producto producto) throws SQLException {
        productoRepo.update(producto);
    }

    public void deleteProducto(int id_producto) throws SQLException {
        productoRepo.delete(id_producto);
    }


}
