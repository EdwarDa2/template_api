package org.alilopez.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.Producto;
import org.alilopez.service.ProductoService;



import java.sql.SQLException;
import java.util.List;

public class ProductoController {
    private final ProductoService productoService;

    public ProductoController(ProductoService productoService   ) {
        this.productoService = productoService;
    }

    public void getAll(Context ctx) {
        try {
            List<Producto> productos = productoService.getAllProductos();
            ctx.json(productos);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener Productos");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_producto"));
            Producto producto = productoService.getById_Producto(id);
            if (producto != null) {
                ctx.json(producto);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Producto no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener productos");
        }
    }

    public void create(Context ctx) {
        try {
            Producto producto = ctx.bodyAsClass(Producto.class);
            productoService.createProducto(producto);
            ctx.status(201).result("Producto creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear Producto");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Producto producto = ctx.bodyAsClass(Producto.class);
            if (producto.getId_producto() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            productoService.updateProducto(producto);
            ctx.status(200).result("Prodcuto actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de producto inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar producto: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            productoService.deleteProducto(id);
            ctx.status(204).result("Producto eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de producto inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar producto: " + e.getMessage());
        }
    }
}
