package org.EdwarDa2.service;

import org.EdwarDa2.repository.StatsRepository;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public class StatsService {
    private final StatsRepository statsRepository;
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Map<String, Object>> obtenerGananciaPorMesa(String fechaInicio, String fechaFin) throws SQLException {
        return statsRepository.getGananciaPorMesa(fechaInicio, fechaFin);
    }
}
