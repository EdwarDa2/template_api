package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.CategoriaController;

public class CategoriaRoutes {
    private final CategoriaController categoriaController;

    public CategoriaRoutes(CategoriaController categoriaController) {
        this.categoriaController = categoriaController;
    }

    public void register(Javalin app) {
        app.get("/categorias", categoriaController::getCategorias);
        app.get("/subcategorias/{categoriaId}", categoriaController::getSubcategorias);
        app.post("/categorias", categoriaController::create);
        app.put("/categorias/{id_categoria}", categoriaController::update);
        app.delete("/categorias/{id_categoria}", categoriaController::delete);
    }
}
