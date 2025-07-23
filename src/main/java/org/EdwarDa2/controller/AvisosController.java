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
        int id = Integer.parseInt(ctx.pathParam("id"));
        Aviso aviso = service.getById_aviso(id);
        if (aviso != null) {
            ctx.json(aviso);
        } else {
            ctx.status(404).result("Aviso no encontrado");
        }
    }

    public void create(Context ctx) throws SQLException {
        AvisoRequestDTO dto = ctx.bodyAsClass(AvisoRequestDTO.class);
        Aviso aviso = new Aviso();
        aviso.setId_admin(dto.id_admin);
        aviso.setContenido(dto.contenido);
        aviso.setFecha(LocalDateTime.now());

        int idGenerado = service.createAviso(aviso);
        if (idGenerado != -1) {
            ctx.status(201).result(String.valueOf(idGenerado));
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