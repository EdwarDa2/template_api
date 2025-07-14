package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.MesasController;

public class MesasRoutes {
    private final MesasController mesasController;
    public MesasRoutes(MesasController mesasController) {this.mesasController = mesasController;}
    public void register(Javalin app) {
        app.get("/mesas", mesasController::getAll);
        app.post("/mesas", mesasController::create);
        app.get("/mesas/{id_mesa}", mesasController::getById);
        app.put("/mesas/{id}", mesasController::update);
        app.delete("/mesas/{id}", mesasController::delete);

    }
}
