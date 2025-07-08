package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.UserController;

public class UserRoutes {
    private final UserController userController;

    public UserRoutes(UserController userController) {
        this.userController = userController;
    }

    public void register(Javalin app) {
        app.get("/usuario", userController::getAll);
        app.post("/usuario", userController::create);
        app.get("/usuario/{id_usuario}", userController::getById);
        app.put("/usuario/{id}", userController::update);
        app.delete("/usuario/{id}", userController::delete);
    }
}
