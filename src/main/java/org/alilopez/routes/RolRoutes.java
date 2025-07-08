package org.alilopez.routes;

import io.javalin.Javalin;
import org.alilopez.controller.RolController;

public class RolRoutes {
    private final RolController rolController;
    public RolRoutes(RolController rolController) {this.rolController = rolController;}
    public void register(Javalin app) {
        app.get("/rol", rolController::getAll);
        app.post("/rol", rolController::create);
        app.get("/rol/{id_rol}", rolController::getById);
        app.put("/rol/{id}", rolController::update);
        app.delete("/rol/{id}", rolController::delete);

    }
}
