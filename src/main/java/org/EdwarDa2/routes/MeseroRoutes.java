package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.MeseroController;

public class MeseroRoutes {
    private final MeseroController meseroController;
    public MeseroRoutes(MeseroController meseroController) {this.meseroController = meseroController;}
    public void register(Javalin app) {
        app.get("/meseros", meseroController::getAll);
        app.post("/meseros", meseroController::create);
        app.get("/meseros/{id_mesero}", meseroController::getById);
        app.put("/meseros/{id}", meseroController::update);
        app.delete("/meseros/{id}", meseroController::delete);

    }
}
