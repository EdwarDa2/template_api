package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.DTO.AdminDTO;
import org.EdwarDa2.model.Admin;
import org.EdwarDa2.service.AdminService;

import java.sql.SQLException;
import java.util.List;

public class AdminController {
    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    public void getAll(Context ctx) {
        try {
            List<Admin> admins = adminService.getAllAdmin();
            ctx.json(admins);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener admins: " + e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_admin"));
            Admin admin = adminService.getById_admin(id);
            if (admin != null) {
                ctx.json(admin);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Admin no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener admins: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            AdminDTO dto = ctx.bodyAsClass(AdminDTO.class);
            adminService.createAdmin(dto);
            ctx.status(201).result("Admin creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear admin: " + e.getMessage());
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Admin admin = ctx.bodyAsClass(Admin.class);
            if (admin.getId_admin() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            adminService.updateAdmin(admin);
            ctx.status(200).result("Admin actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de admin inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar admin: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            adminService.deleteAdmin(id);
            ctx.status(204).result("Admin eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de admin inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar admin: " + e.getMessage());
        }
    }
}
