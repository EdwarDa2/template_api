package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.AdminController;

public class AdminRoutes {
    private final AdminController adminController;
    public AdminRoutes(AdminController adminController) {this.adminController = adminController;}
    public void register(Javalin app) {
        app.get("/admins", adminController::getAll);
        app.post("/admins", adminController::create);
        app.get("/admins/{id_admin}", adminController::getById);
        app.put("/admins/{id}", adminController::update);
        app.delete("/admins/{id}", adminController::delete);

    }
}
