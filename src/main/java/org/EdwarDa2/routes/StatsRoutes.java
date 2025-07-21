package org.EdwarDa2.routes;

import io.javalin.Javalin;
import org.EdwarDa2.controller.StatsController;

public class StatsRoutes {
    private final StatsController statsController;
    public StatsRoutes(StatsController statsController) {
        this.statsController = statsController;
    }
    public void register(Javalin app) {
        app.get("/stats/average", statsController::obtenerGananciaPorMesa);
    }
}
