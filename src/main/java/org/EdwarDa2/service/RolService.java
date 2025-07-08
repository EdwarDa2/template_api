package org.EdwarDa2.service;

import org.EdwarDa2.model.Rol;
import org.EdwarDa2.repository.RolRepository;

import java.sql.SQLException;
import java.util.List;

public class RolService {
    private final RolRepository rolRepo;
    public RolService(RolRepository rolRepo) {
        this.rolRepo = rolRepo;
    }
    public List<Rol> getAllRol() throws SQLException {
        return rolRepo.findAll();
    }

    public Rol getById_rol(int id_rol) throws SQLException {
        return rolRepo.findById_rol(id_rol);
    }

    public void createRol(Rol rol) throws SQLException {

        rolRepo.save(rol);
    }
    public void updateRol(Rol rol) throws SQLException {
        rolRepo.update(rol);
    }

    public void deleteRol(int id_rol) throws SQLException {
        rolRepo.delete(id_rol);
    }
}
