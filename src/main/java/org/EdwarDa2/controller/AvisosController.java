package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Aviso;
import org.EdwarDa2.service.AvisosService;

import java.sql.SQLException;
import java.util.List;

public class AvisosController {
    private final AvisosService avisosService;

    public AvisosController(AvisosService avisosService   ) {
        this.avisosService = avisosService;
    }

    public void getAll(Context ctx) {
        try {
            List<Aviso> avisos = avisosService.getAllAviso();
            ctx.json(avisos);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener Avisos");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("Id_aviso"));
            Aviso aviso = avisosService.getById_aviso(id);
            if (aviso != null) {
                ctx.json(aviso);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("AVISO no encontrada");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener avisos");
        }
    }

    public void create(Context ctx) {
        try {
            Aviso aviso = ctx.bodyAsClass(Aviso.class);
            avisosService.createAviso(aviso);
            ctx.status(201).result("aviso creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear aviso");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Aviso aviso = ctx.bodyAsClass(Aviso.class);
            if (aviso.getId_aviso() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            avisosService.updateAviso(aviso);
            ctx.status(200).result("Aviso actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de aviso inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar aviso: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            avisosService.deleteAviso(id);
            ctx.status(204).result("Aviso eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de aviso inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar aviso: " + e.getMessage());
        }
    }

}
