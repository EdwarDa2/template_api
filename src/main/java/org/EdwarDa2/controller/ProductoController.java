package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.DTO.ProductoDTO;
import org.EdwarDa2.DTO.ProductoRequestDTO.ProductoRequestDTO;
import org.EdwarDa2.model.Categoria;
import org.EdwarDa2.model.Producto;
import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.service.CategoriaService;
import org.EdwarDa2.service.ProductoService;
import org.EdwarDa2.service.SubcategoriaService;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

public class ProductoController {
    private final ProductoService productoService;
    private final CategoriaService categoriaService;
    private final SubcategoriaService subcategoriaService;

    public ProductoController(ProductoService productoService, CategoriaService categoriaService, SubcategoriaService subcategoriaService) {
        this.productoService = productoService;
        this.categoriaService = categoriaService;
        this.subcategoriaService = subcategoriaService;
    }

    public void getAll(Context ctx) {
        try {
            List<Producto> productos = productoService.getAllProductos();
            ctx.json(productos);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener Productos");
        }
    }

    public void getBySubcategoria(Context ctx) {
        try {
            int id_subcategoria = Integer.parseInt(ctx.pathParam("id_subcategoria"));
            List<Producto> productos = productoService.getBySubcategoria(id_subcategoria);

            if (productos.isEmpty()) {
                ctx.status(HttpStatus.NOT_FOUND).result("No se encontraron productos");
            } else {
                ctx.json(productos); // Devuelve la lista de productos como JSON
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener productos: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            Producto producto = ctx.bodyAsClass(Producto.class);  // Deserializa el producto desde el cuerpo de la solicitud

            // Usamos el ID de la categoría para obtener la categoría del repositorio
            Categoria categoria = categoriaService.fetchCategoriaById(producto.getCategoriaId());  // Usamos el ID de la categoría
            Subcategoria subcategoria = subcategoriaService.fetchSubcategoriaById(producto.getSubcategoriaId());  // Usamos el ID de la subcategoría

            // Guardar el producto en la base de datos
            productoService.createProducto(producto);

            // Responder con los datos del producto, categoría y subcategoría
            ctx.status(201).json(new HashMap<String, Object>() {{
                put("message", "Producto creado");
                put("producto", producto);
                put("categoriaNombre", categoria.getNombre_categoria());
                put("subcategoriaNombre", subcategoria.getNombre_subcategoria());
            }});

        } catch (Exception e) {
            e.printStackTrace();
            // Devolver error si hay un problema
            ctx.status(400).json(new HashMap<String, Object>() {{
                put("message", "Error al crear Producto");
                put("error", e.getMessage());
            }});
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
            System.out.println("Eliminando producto con ID: " + id);  // Agrega un log para ver el ID que se recibe

            // Eliminar el producto desde el servicio
            productoService.deleteProducto(id);

            // Responder con un mensaje de éxito
            ctx.status(200).json(new HashMap<String, Object>() {{
                put("message", "Producto eliminado correctamente");
            }});

        } catch (Exception e) {
            e.printStackTrace();
            // Responder con un error si falla la eliminación
            ctx.status(400).json(new HashMap<String, Object>() {{
                put("message", "Error al eliminar el producto");
                put("error", e.getMessage());
            }});
        }
    }

    public void createPro(Context ctx) throws SQLException {
        ProductoRequestDTO productoDTO = ctx.bodyAsClass(ProductoRequestDTO.class);
        Producto producto = productoService.createPro(productoDTO);
        ctx.status(201).json(producto);
    }


    public void getAllPro(Context ctx) throws SQLException {
        List<Producto> productos = productoService.getAllPro();

        List<ProductoDTO> productoDTOs = productos.stream().map(producto -> {
            ProductoDTO dto = new ProductoDTO();
            dto.setNombre(producto.getNombre());
            dto.setPrecio(producto.getPrecio());
            dto.setCategoria(producto.getSubcategoria().    getCategoria().getNombre_categoria());
            dto.setSubcategoria(producto.getSubcategoria().getNombre_subcategoria());
            return dto;
        }).collect(Collectors.toList());

        ctx.json(productoDTOs);
    }
}
