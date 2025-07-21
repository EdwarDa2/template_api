package org.EdwarDa2.service;

import org.EdwarDa2.model.Aviso;
import org.EdwarDa2.repository.AvisosRepository;

import java.sql.SQLException;
import java.util.List;

public class AvisosService {
    private final AvisosRepository avisosRepo;

    public AvisosService(AvisosRepository avisosRepo) {
        this.avisosRepo = avisosRepo;
    }

    public List<Aviso> getAllAviso() throws SQLException {
        return avisosRepo.findAll();
    }

    public Aviso getById_aviso(int id_aviso) throws SQLException {
        return avisosRepo.findById_aviso(id_aviso);
    }

    public int createAviso(Aviso aviso) throws SQLException {
        return avisosRepo.insert(aviso);
    }

    public void updateAviso(Aviso aviso) throws SQLException {
        avisosRepo.update(aviso);
    }

    public void deleteAviso(int id_aviso) throws SQLException {
        avisosRepo.delete(id_aviso);
    }
}
