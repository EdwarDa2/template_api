package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.DTO.MeseroDTO;
import org.EdwarDa2.model.Mesero;
import org.EdwarDa2.service.MeseroService;

import java.sql.SQLException;
import java.util.List;

public class MeseroController {
    private final MeseroService meseroService;

    public MeseroController(MeseroService meseroService) {
        this.meseroService = meseroService;
    }

    public void getAll(Context ctx) {
        try {
            List<Mesero> meseros = meseroService.getAllMesero();
            ctx.json(meseros);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener meseros: " + e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_mesero"));
            Mesero mesero = meseroService.getById_mesero(id);
            if (mesero != null) {
                ctx.json(mesero);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Mesero no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener meseros: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            MeseroDTO dto = ctx.bodyAsClass(MeseroDTO.class);
            meseroService.createMesero(dto);
            ctx.status(201).result("Mesero creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear mesero: " + e.getMessage());
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Mesero mesero = ctx.bodyAsClass(Mesero.class);
            if (mesero.getId_mesero() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            meseroService.updateMesero(mesero);
            ctx.status(200).result("Mesero actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de mesero inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar mesero: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            meseroService.deleteMesero(id);
            ctx.status(204).result("Mesero eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de mesero inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar mesero: " + e.getMessage());
        }
    }
}
