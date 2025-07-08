package org.alilopez.service;

import org.alilopez.model.Mesero;
import org.alilopez.repository.MeseroRepository;

import java.sql.SQLException;
import java.util.List;

public class MeseroService {
    private final MeseroRepository meseroRepo;
    public MeseroService(MeseroRepository meseroRepo) {
        this.meseroRepo = meseroRepo;
    }
    public List<Mesero> getAllMesero() throws SQLException {
        return meseroRepo.findAll();
    }

    public Mesero getById_mesero(int id_mesero) throws SQLException {
        return meseroRepo.findById_mesero(id_mesero);
    }

    public void createMesero(Mesero mesero) throws SQLException {

        meseroRepo.save(mesero);
    }
    public void updateMesero(Mesero mesero) throws SQLException {
        meseroRepo.update(mesero);
    }

    public void deleteMesero(int id_mesero) throws SQLException {
        meseroRepo.delete(id_mesero);
    }
}
