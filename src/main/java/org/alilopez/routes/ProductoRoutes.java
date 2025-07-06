package org.alilopez.routes;

import io.javalin.Javalin;
import org.alilopez.controller.ProductoController;

public class ProductoRoutes {
    private final ProductoController productoController;
    public ProductoRoutes(ProductoController productoController) {this.productoController = productoController;}
    public void register(Javalin app) {
        app.get("/productos", productoController::getAll);
        app.post("/productos", productoController::create);
        app.get("/productos/{id_producto}", productoController::getById);
        app.put("/productos/{id}", productoController::update);
        app.delete("/productos/{id}", productoController::delete);

    }
}
