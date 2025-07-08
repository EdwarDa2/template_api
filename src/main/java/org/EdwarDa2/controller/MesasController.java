package org.EdwarDa2.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Mesa;
import org.EdwarDa2.service.MesasService;
import java.sql.SQLException;
import java.util.List;



public class MesasController {
    private final MesasService mesasService;

    public MesasController(MesasService mesasService   ) {
        this.mesasService = mesasService;
    }

    public void getAll(Context ctx) {
        try {
            List<Mesa> mesas = mesasService.getAllMesas();
            ctx.json(mesas);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener Mesas");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_mesa"));
            Mesa mesa = mesasService.getById_mesa(id);
            if (mesa != null) {
                ctx.json(mesa);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Mesa no encontrada");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener Mesas");
        }
    }

    public void create(Context ctx) {
        try {
            Mesa mesa = ctx.bodyAsClass(Mesa.class);
            mesasService.createMesa(mesa);
            ctx.status(201).result("mesa creada");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear Mesa");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Mesa mesa = ctx.bodyAsClass(Mesa.class);
            if (mesa.getId_mesa() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            mesasService.updateMesa(mesa);
            ctx.status(200).result("Mesa actualizada exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de mesa inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar mesa: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            mesasService.deleteMesa(id);
            ctx.status(204).result("Mesa eliminada exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de mesa inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar mesa: " + e.getMessage());
        }
    }
}
