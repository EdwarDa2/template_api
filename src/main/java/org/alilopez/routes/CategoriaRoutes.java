package org.alilopez.routes;

import io.javalin.Javalin;
import org.alilopez.controller.CategoriaController;

public class CategoriaRoutes {
    private final CategoriaController categoriaController;
    public CategoriaRoutes(CategoriaController categoriaController) {this.categoriaController = categoriaController;}
    public void register(Javalin app) {
        app.get("/categorias", categoriaController::getAll);
        app.post("/categorias", categoriaController::create);
        app.get("/categorias/{id_categoria}", categoriaController::getById);
        app.put("/categorias/{id}", categoriaController::update);
        app.delete("/categorias/{id}", categoriaController::delete);
    }
}
