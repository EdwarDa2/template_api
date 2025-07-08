package org.EdwarDa2.service;

import org.EdwarDa2.model.Total;
import org.EdwarDa2.repository.TotalRepository;

import java.sql.SQLException;
import java.util.List;

public class TotalService {
    private final TotalRepository totalRepo;
    public TotalService(TotalRepository totalRepo) {
        this.totalRepo = totalRepo;
    }
    public List<Total> getAllTotal() throws SQLException {
        return totalRepo.findAll();
    }

    public Total getById_total(int id_total) throws SQLException {
        return totalRepo.findById_total(id_total);
    }

    public void createTotal(Total total) throws SQLException {

        totalRepo.save(total);
    }
    public void updateTotal(Total total) throws SQLException {
        totalRepo.update(total);
    }

    public void deleteTotal(int id_total) throws SQLException {
        totalRepo.delete(id_total);
    }
}
