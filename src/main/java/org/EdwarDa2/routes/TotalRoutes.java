package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.TotalController;

public class TotalRoutes {
    private final TotalController totalController;
    public TotalRoutes(TotalController totalController) {this.totalController = totalController;}
    public void register(Javalin app) {
        app.get("/total", totalController::getAll);
        app.post("/total", totalController::create);
        app.get("/total/{id_total}", totalController::getById);
        app.put("/total/{id}", totalController::update);
        app.delete("/total/{id}", totalController::delete);
    }
}
