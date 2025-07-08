package org.alilopez.routes;

import io.javalin.Javalin;
import org.alilopez.controller.ComandaController;

public class ComandaRoutes {
        private final ComandaController comandaController;
        public ComandaRoutes(ComandaController comandaController) {this.comandaController = comandaController;}
        public void register(Javalin app) {
            app.get("/comandas", comandaController::getAll);
            app.post("/comandas", comandaController::create);
            app.get("/comandas/{id_comanda}", comandaController::getById);
            app.put("/comandas/{id}", comandaController::update);
            app.delete("/comandas/{id}", comandaController::delete);

        }
}
