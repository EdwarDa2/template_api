package org.EdwarDa2.controller;

import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.model.Total;
import org.EdwarDa2.service.TotalService;

import java.sql.SQLException;
import java.util.List;

public class TotalController {
    private final TotalService totalService;
    public TotalController(TotalService totalservice) {
        this.totalService = totalservice;
    }

    public void getAll(Context ctx) {
        try {
            List<Total> totals = totalService.getAllTotal();
            ctx.json(totals);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener el total: " + e.getMessage());
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_total"));
            Total total = totalService.getById_total(id);
            if (total != null) {
                ctx.json(total);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Total no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener totales: " + e.getMessage());
        }
    }

    public void create(Context ctx) {
        try {
            Total total = ctx.bodyAsClass(Total.class);
            totalService.createTotal(total);
            ctx.status(201).result("Total creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear total: " + e.getMessage());
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            Total total = ctx.bodyAsClass(Total.class);
            if (total.getId_total() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            totalService.updateTotal(total);
            ctx.status(200).result("Total actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de total inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar total: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            totalService.deleteTotal(id);
            ctx.status(204).result("total eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de total inválido");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar total: " + e.getMessage());
        }
    }
}
