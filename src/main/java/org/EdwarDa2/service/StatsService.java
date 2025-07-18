package org.EdwarDa2.service;

import org.EdwarDa2.model.Stat;
import org.EdwarDa2.repository.StatsRepository;

import java.sql.SQLException;
import java.util.List;

public class StatsService {
    private final StatsRepository statsRepository;
    public StatsService(StatsRepository statsRepository) {
        this.statsRepository = statsRepository;
    }

    public List<Stat> createStatsAverage() throws SQLException {
        return statsRepository.createStatsAverage();
    }
}
