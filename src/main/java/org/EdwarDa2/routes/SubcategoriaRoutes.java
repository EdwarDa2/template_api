package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.service.ISubcategoriaService;

public class SubcategoriaRoutes {

    private final ISubcategoriaService service;

    public SubcategoriaRoutes(ISubcategoriaService service) {
        this.service = service;
    }

    public void register(Javalin app) {

        app.get("/subcategorias", ctx -> {
            ctx.json(service.getAllSubcategoria());
        });

        app.get("/subcategorias/{id_subcategoria}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id_subcategoria"));
            Subcategoria sub = service.getById_subcategoria(id);
            if (sub != null) {
                ctx.json(sub);
            } else {
                ctx.status(404).result("Subcategoría no encontrada");
            }
        });

        app.post("/subcategorias", ctx -> {
            Subcategoria nueva = ctx.bodyAsClass(Subcategoria.class);
            service.createSubcategoria(nueva);
            ctx.status(201).result("Subcategoría creada correctamente");
        });

        app.put("/subcategorias", ctx -> {
            Subcategoria sub = ctx.bodyAsClass(Subcategoria.class);
            service.updateSubcategoria(sub);
            ctx.status(200).result("Subcategoría actualizada");
        });

        app.delete("/subcategorias/{id_subcategoria}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id_subcategoria"));
            service.deleteSubcategoria(id);
            ctx.status(200).result("Subcategoría eliminada");
        });

        app.get("/subcategorias/categoria/{id_categoria}", ctx -> {
            int id = Integer.parseInt(ctx.pathParam("id_categoria"));
            ctx.json(service.getByCategoria(id));
        });
    }
}
