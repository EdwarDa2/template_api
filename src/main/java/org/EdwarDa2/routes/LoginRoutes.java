package org.EdwarDa2.routes;

import org.EdwarDa2.controller.LoginController;
import io.javalin.Javalin;

public class LoginRoutes {

    private final LoginController loginController;

    public LoginRoutes(LoginController loginController) {
        this.loginController = loginController;
    }

    public void register(Javalin app) {
        app.post("/login", loginController::loginUser);
    }
}
