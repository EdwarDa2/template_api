package org.EdwarDa2.controller;

import io.javalin.http.Context;
import org.EdwarDa2.repository.StatsRepository;
import org.EdwarDa2.service.StatsService;

import java.sql.SQLException;

import java.util.List;
import java.util.Map;

public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }
    public void obtenerGananciaPorMesa(Context ctx) {
        String fechaInicio = ctx.queryParam("fecha_inicio");
        String fechaFin = ctx.queryParam("fecha_fin");
        try {
            // Se delega al servicio inyectado en lugar de instanciar StatsRepository directamente
            List<Map<String, Object>> data = statsService.obtenerGananciaPorMesa(fechaInicio, fechaFin);
            ctx.json(data);
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Error al obtener ganancias filtradas por fecha" + e.getMessage());
        }
    }


}
