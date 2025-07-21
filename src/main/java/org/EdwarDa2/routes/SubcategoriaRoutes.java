package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.SubcategoriaController;

public class SubcategoriaRoutes {
    private final SubcategoriaController subcategoriaController;
    public SubcategoriaRoutes(SubcategoriaController subcategoriaController) {this.subcategoriaController = subcategoriaController;}
    public void register(Javalin app) {
        app.get("/subcategorias", subcategoriaController::getAll);
        app.post("/subcategorias", subcategoriaController::create);
        app.get("/subcategorias/{id_subcategoria}", subcategoriaController::getById);
        app.put("/subcategorias/{id}", subcategoriaController::update);
        app.delete("/subcategorias/{id}", subcategoriaController::delete);
    }
}
