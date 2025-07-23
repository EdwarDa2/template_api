package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.UserController;

public class UserRoutes {
    private final UserController userController;

    public UserRoutes(UserController userController) {
        this.userController = userController;
    }

    public void register(Javalin app) {
        app.get("/usuarios", userController::getAll);
        app.post("/usuarios", userController::create);
        app.get("/usuarios/{id_usuario}", userController::getById);
        app.put("/usuarios/{id}", userController::update);
        app.delete("/usuarios/{id}", userController::delete);
    }
}
