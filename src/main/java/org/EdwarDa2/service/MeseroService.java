package org.EdwarDa2.service;

import org.EdwarDa2.DTO.MeseroDTO;
import org.EdwarDa2.model.Mesero;
import org.EdwarDa2.model.User;
import org.EdwarDa2.repository.MeseroRepository;
import org.EdwarDa2.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class MeseroService {
    private final MeseroRepository meseroRepo;
    private final UserRepository userRepo;

    public MeseroService(MeseroRepository meseroRepo) {
        this.meseroRepo = meseroRepo;
        this.userRepo = new UserRepository();
    }

    public List<Mesero> getAllMesero() throws SQLException {
        return meseroRepo.findAll();
    }

    public Mesero getById_mesero(int id_mesero) throws SQLException {
        return meseroRepo.findById_mesero(id_mesero);
    }

    public void createMesero(MeseroDTO dto) throws SQLException {
        // 1. Crear entidad User
        User user = new User(dto.getNombre(), dto.getApellido_p(), dto.getApellido_m(), dto.getRol());

        int idUser = userRepo.save(user);

        // 2. Crear Mesero
        Mesero mesero = new Mesero(idUser, dto.getClave());
        meseroRepo.save(mesero);
    }
    public void updateMesero(Mesero mesero) throws SQLException {
        meseroRepo.update(mesero);
    }

    public void deleteMesero(int id_mesero) throws SQLException {
        meseroRepo.delete(id_mesero);
    }
}
