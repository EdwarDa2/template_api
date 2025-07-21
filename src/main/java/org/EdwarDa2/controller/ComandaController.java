package org.EdwarDa2.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.DTO.comandas.ComandaRequestDTO;
import org.EdwarDa2.model.Comanda;
import org.EdwarDa2.service.ComandaService;
import java.sql.SQLException;
import java.util.List;

public class ComandaController {
        private final ComandaService comandaService;

        public ComandaController(ComandaService comandaService   ) {
            this.comandaService = comandaService;
        }

        public void getAll(Context ctx) {
            try {
                List<ComandaRequestDTO> comandas = comandaService.getAllComanda();
                ctx.json(comandas);
            } catch (SQLException e) {
                ctx.status(500).result("Error al obtener Comandas" + e.getMessage());
            }
        }

        public void getById(Context ctx) {
            try {
                int id = Integer.parseInt(ctx.pathParam("id_comanda"));
                Comanda comanda = comandaService.getById_comanda(id);
                if (comanda != null) {
                    ctx.json(comanda);
                } else {
                    ctx.status(HttpStatus.NOT_FOUND).result("comanda no encontrada");
                }
            } catch (Exception e) {
                ctx.status(404).result("Error al obtener comandas"+e.getMessage());
            }
        }

        public void create(Context ctx) {
            try {
                ComandaRequestDTO comanda = ctx.bodyAsClass(ComandaRequestDTO.class);
                comandaService.createComanda(comanda);
                ctx.status(201).result("comanda creada");
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(400).result("Error al crear Comanda"+e.getMessage());
            }
        }
        public void delete(Context ctx) {
            try {
                int id = Integer.parseInt(ctx.pathParam("id"));
                comandaService.deleteComanda(id);
                ctx.status(200).result("Comanda eliminada exitosamente");
            } catch (NumberFormatException e) {
                ctx.status(400).result("ID de comanda inválido."+e.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
                ctx.status(500).result("Error al eliminar comanda: " + e.getMessage());
            }
        }


}
