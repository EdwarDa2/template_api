package org.EdwarDa2.service;
import org.EdwarDa2.model.Producto;
import org.EdwarDa2.repository.ProductoRepository;


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

    public List<Producto> getBySubcategoria(int id_subcategoria) throws SQLException {
        return productoRepo.findBySubcategoria(id_subcategoria);
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
