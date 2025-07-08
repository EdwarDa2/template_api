package org.alilopez.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.Categoria;
import org.alilopez.service.CategoriaService;
import java.sql.SQLException;
import java.util.List;

public class CategoriaController {

        private final CategoriaService categoriaService;

        public CategoriaController(CategoriaService categoriaService   ) {
            this.categoriaService = categoriaService;
        }

        public void getAll(Context ctx) {
            try {
                List<Categoria> categorias = categoriaService.getAllCategoria();
                ctx.json(categorias);
            } catch (SQLException e) {
                ctx.status(500).result("Error al obtener Categorias");
            }
        }

        public void getById(Context ctx) {
            try {
                int id = Integer.parseInt(ctx.pathParam("id_categoria"));
                Categoria categoria = categoriaService.getById_categoria(id);
                if (categoria != null) {
                    ctx.json(categoria);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("categoria no encontrada");
                }
            } catch (Exception e) {
                ctx.status(404).result("Error al obtener Categorias");
            }
        }

        public void create(Context ctx) {
            try {
                Categoria categoria = ctx.bodyAsClass(Categoria.class);
                categoriaService.createCategoria(categoria);
                ctx.status(201).result("categoria creada");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(400).result("Error al crear categoria");
            }
        }
        public void update(Context ctx) {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                Categoria categoria = ctx.bodyAsClass(Categoria.class);
                if (categoria.getId_categoria() != id) {
                    ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                    return;
                }
                categoriaService.updateCategoria(categoria);
                ctx.status(200).result("Categoria actualizada exitosamente");
            } catch (NumberFormatException e) {
                ctx.status(400).result("ID de categoria inválido.");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Error al actualizar categoria: " + e.getMessage());
            }
        }
        public void delete(Context ctx) {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                categoriaService.deleteCategoria(id);
                ctx.status(204).result("Categoria eliminada exitosamente");
            } catch (NumberFormatException e) {
                ctx.status(400).result("ID de categoria inválido.");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Error al eliminar categoria: " + e.getMessage());
            }
        }


}
