package org.EdwarDa2.controller;

import io.javalin.http.Context;
import org.EdwarDa2.DTO.StatsDTO;
import org.EdwarDa2.model.Stat;
import org.EdwarDa2.service.StatsService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class StatsController {
    private final StatsService statsService;

    public StatsController(StatsService statsService) {
        this.statsService = statsService;
    }

    public void createStatsAverage(Context ctx) {
        try {
            List<Stat> stats = statsService.createStatsAverage();
            List<String> tempName = new ArrayList<>();
            List<Float> tempPrice = new ArrayList<>();
            List<Integer> tempAmount = new ArrayList<>();
            for (Stat stat : stats) {
                tempName.add(stat.getName());
                tempPrice.add(stat.getPrice());
                tempAmount.add(stat.getAmount());
            }
            StatsDTO statsDTO = new StatsDTO(tempName, tempPrice,tempAmount);
            Map<String, Object> result = Map.of(
                    "name", stats.stream().map(Stat::getName).toList(),
                    "price", stats.stream().map(Stat::getPrice).toList(),
                    "amount", stats.stream().map(Stat::getAmount).toList()
            );
            ctx.json(result);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            ctx.status(500).result("Error al obtener productos");
        }
    }
}
