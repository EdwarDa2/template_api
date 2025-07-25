package org.EdwarDa2.controller;

import org.EdwarDa2.model.LoginRequest;
import org.EdwarDa2.service.LoginService;
import org.EdwarDa2.model.Usuario;
import io.javalin.http.Context;

public class LoginController {

    private final LoginService loginService;

    public LoginController(LoginService loginService) {
        this.loginService = loginService;
    }

    public void loginUser(Context ctx) {
        LoginRequest loginRequest = ctx.bodyAsClass(LoginRequest.class);
        String clave = loginRequest.getClave();

        Usuario usuario = loginService.validarPorClave(clave);

        if (usuario != null) {
            ctx.json(usuario);
        } else {
            ctx.status(401).result("Credenciales inválidas");
        }
    }
}
