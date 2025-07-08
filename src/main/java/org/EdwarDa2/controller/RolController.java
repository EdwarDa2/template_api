package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Rol;
import org.EdwarDa2.service.RolService;

import java.sql.SQLException;
import java.util.List;

public class RolController {
    private final RolService rolService;

    public RolController(RolService rolService   ) {
        this.rolService = rolService;
    }

    public void getAll(Context ctx) {
        try {
            List<Rol> rols = rolService.getAllRol();
            ctx.json(rols);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener roles");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_rol"));
            Rol rol = rolService.getById_rol(id);
            if (rol != null) {
                ctx.json(rol);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Rol no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener Rol");
        }
    }

    public void create(Context ctx) {
        try {
            Rol rol = ctx.bodyAsClass(Rol.class);
            rolService.createRol(rol);
            ctx.status(201).result("Rol creada");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear rol");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Rol rol = ctx.bodyAsClass(Rol.class);
            if (rol.getId_rol() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            rolService.updateRol(rol);
            ctx.status(200).result("Rol actualizada exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de rol inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar rol: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            rolService.deleteRol(id);
            ctx.status(204).result("Rol eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de rol inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar rol: " + e.getMessage());
        }
    }
}
