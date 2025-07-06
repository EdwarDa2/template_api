package org.alilopez.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.alilopez.model.User;
import org.alilopez.service.UserService;
import java.sql.SQLException;
import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getAll(Context ctx) {
        try {
            List<User> usuario = userService.getAllUsuario();
            ctx.json(usuario);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener usuarios");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_usuario"));
            User user = userService.getByIdUser(id);
            if (user != null) {
                ctx.json(user);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Usuario no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener usuarios");
        }
    }

    public void create(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            userService.createUser(user);
            ctx.status(201).result("Usuario creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear usuario");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            User user = ctx.bodyAsClass(User.class);
            if (user.getId_usuario() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            userService.updateUser(user);
            ctx.status(200).result("Usuario actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar usuario: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            userService.deleteUser(id);
            ctx.status(204).result("Usuario eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar usuario: " + e.getMessage());
        }
    }
}







