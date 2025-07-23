package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.ProductoController;

public class ProductoRoutes {
    private final ProductoController productoController;

    public ProductoRoutes(ProductoController productoController) {
        this.productoController = productoController;
    }

    public void register(Javalin app) {
        app.get("/productos", productoController::getAll);
        app.post("/productos", productoController::create);
        app.get("/productos/subcategoria/{id_subcategoria}", productoController::getBySubcategoria);
        app.put("/productos/{id}", productoController::update);
        app.delete("/productos/{id}", productoController::delete);
        app.post("/productos/item", productoController::createPro);
        app.get("/productos/item", productoController::getAllPro);
    }
}
