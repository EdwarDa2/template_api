package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.AvisosController;

public class AvisosRoutes {
    private final AvisosController avisosController;
    public AvisosRoutes(AvisosController avisosController) {this.avisosController = avisosController;}
    public void register(Javalin app) {
        app.get("/avisos", avisosController::getAll);
        app.post("/avisos", avisosController::create);
        app.get("/avisos/{id_aviso}", avisosController::getById);
        app.put("/avisos/{id}", avisosController::update);
        app.delete("/avisos/{id}", avisosController::delete);

    }
}
