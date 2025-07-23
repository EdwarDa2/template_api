package org.EdwarDa2.controller;

import io.javalin.http.Context;
import org.EdwarDa2.DTO.comandas.AvisoRequestDTO;
import org.EdwarDa2.model.Aviso;
import org.EdwarDa2.service.AvisosService;

import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;

public class AvisosController {
    private final AvisosService service;

    public AvisosController(AvisosService service) {
        this.service = service;
    }

    public void getAll(Context ctx) throws SQLException {
        List<Aviso> avisos = service.getAllAviso();
        ctx.json(avisos);
    }

    public void getById(Context ctx) throws SQLException {
        // La ruta usa {id_aviso}, por lo que hay que obtener ese parámetro
        int id = Integer.parseInt(ctx.pathParam("id_aviso"));
        Aviso aviso = service.getById_aviso(id);
        if (aviso != null) {
            ctx.json(aviso);
        } else {
            ctx.status(404).result("Aviso no encontrado");
        }
    }

    public void create(Context ctx) throws SQLException {
        // El cliente puede enviar "id_admin" o "id_usuario"; soportamos ambos
        Aviso incoming = ctx.bodyAsClass(Aviso.class);
        Aviso aviso = new Aviso();
        int idAdmin = incoming.getId_admin();
        // Si no se envió id_admin, intentamos leer id_usuario desde el cuerpo
        if (idAdmin == 0) {
            try {
                java.util.Map<?,?> map = ctx.bodyAsClass(java.util.Map.class);
                Object idUsuarioObj = map.get("id_usuario");
                if (idUsuarioObj instanceof Number) {
                    idAdmin = ((Number) idUsuarioObj).intValue();
                }
            } catch (Exception ignored) {
                // Si falla, dejamos idAdmin en cero y la base de datos lo rechazará
            }
        }
        aviso.setId_admin(idAdmin);
        aviso.setContenido(incoming.getContenido());
        // Si se envía fecha, la usamos; de lo contrario, usamos la fecha/hora actual
        if (incoming.getFecha() != null) {
            aviso.setFecha(incoming.getFecha());
        } else {
            aviso.setFecha(LocalDateTime.now());
        }
        int idGenerado = service.createAviso(aviso);
        if (idGenerado != -1) {
            // Devolvemos un JSON con el id generado
            java.util.Map<String, Object> response = new java.util.HashMap<>();
            response.put("id_aviso", idGenerado);
            ctx.status(201).json(response);
        } else {
            ctx.status(500).result("Error al crear aviso");
        }
    }

    public void update(Context ctx) throws SQLException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        Aviso aviso = ctx.bodyAsClass(Aviso.class);
        aviso.setId_aviso(id);
        service.updateAviso(aviso);
        ctx.status(204);
    }

    public void delete(Context ctx) throws SQLException {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.deleteAviso(id);
        ctx.status(204);
    }
}