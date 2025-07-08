package org.EdwarDa2.service;
import org.EdwarDa2.model.Mesa;
import org.EdwarDa2.repository.MesasRepository;
import java.sql.SQLException;
import java.util.List;


public class MesasService {
    private final MesasRepository mesasRepo;
    public MesasService(MesasRepository mesasRepo) {
        this.mesasRepo = mesasRepo;
    }
    public List<Mesa> getAllMesas() throws SQLException {
        return mesasRepo.findAll();
    }

    public Mesa getById_mesa(int id_mesa) throws SQLException {
        return mesasRepo.findById_mesa(id_mesa);
    }

    public void createMesa(Mesa mesa) throws SQLException {

        mesasRepo.save(mesa);
    }
    public void updateMesa(Mesa mesa) throws SQLException {
        mesasRepo.update(mesa);
    }

    public void deleteMesa(int id_mesa) throws SQLException {
        mesasRepo.delete(id_mesa);
    }


}

