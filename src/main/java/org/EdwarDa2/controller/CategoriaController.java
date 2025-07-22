package org.EdwarDa2.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Categoria;
import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.service.CategoriaService;
import java.sql.SQLException;
import java.util.List;

public class CategoriaController {

        private final CategoriaService categoriaService;

        public CategoriaController(CategoriaService categoriaService   ) {
            this.categoriaService = categoriaService;
        }

    public void getCategorias(Context ctx) {
            try {
                List<Categoria> categorias = categoriaService.getCategorias();
                ctx.json(categorias);
            } catch (Exception e) {
                ctx.status(404).result("Error al obtener los categorias"+e.getMessage());
            }
    }

    public void getSubcategorias(Context ctx) {
            try {
                int categoriaId = Integer.parseInt(ctx.pathParam("categoriaId"));
                List<Subcategoria> subcategorias = categoriaService.getSubcategorias(categoriaId);
                ctx.json(subcategorias);
            } catch (Exception e) {
                ctx.status(404).result("Error al obtener los categorias"+e.getMessage());
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

        public void delete(Context ctx)  {
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
