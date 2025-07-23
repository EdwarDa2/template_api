package org.EdwarDa2.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.service.SubcategoriaService;
import java.sql.SQLException;
import java.util.List;

public class SubcategoriaController {

    private final SubcategoriaService subcategoriaService;

    public SubcategoriaController(SubcategoriaService subcategoriaService   ) {
        this.subcategoriaService = subcategoriaService;
    }

    public void getAll(Context ctx) {
        try {
            List<Subcategoria> subcategorias = subcategoriaService.getAllSubcategoria();
            ctx.json(subcategorias);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener Subcategorias" +e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_subcategoria"));
            Subcategoria subcategoria = subcategoriaService.getById_subcategoria(id);
            if (subcategoria != null) {
                ctx.json(subcategoria);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("subcategoria no encontrada");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener Subcategorias");
        }
    }

    public void create(Context ctx) {
        try {
            Subcategoria subcategoria = ctx.bodyAsClass(Subcategoria.class);
            subcategoriaService.createSubcategoria(subcategoria);
            ctx.status(201).result("subcategoria creada");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear subcategoria");
        }
    }

    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Subcategoria subcategoria = ctx.bodyAsClass(Subcategoria.class);
            if (subcategoria.getId_subcategoria() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            subcategoriaService.updateSubcategoria(subcategoria);
            ctx.status(200).result("Subcategoria actualizada exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de subcategoria inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar subcategoria: " + e.getMessage());
        }
    }

    public void delete(Context ctx)  {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            subcategoriaService.deleteSubcategoria(id);
            ctx.status(204).result("Subcategoria eliminada exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de subcategoria inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar subcategoria: " + e.getMessage());
        }
    }


}