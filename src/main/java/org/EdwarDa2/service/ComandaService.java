package org.EdwarDa2.service;

import org.EdwarDa2.DTO.comandas.ComandaRequestDTO;
import org.EdwarDa2.model.Comanda;
import org.EdwarDa2.repository.ComandaRepository;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
public class ComandaService {
        private final ComandaRepository comandaRepo;
        public ComandaService(ComandaRepository comandaRepo) {
            this.comandaRepo = comandaRepo;
        }
        public List<ComandaRequestDTO> getAllComanda() throws SQLException {
            return comandaRepo.findAll();
        }

        public Comanda getById_comanda(int id_comanda) throws SQLException {
            return comandaRepo.findById_comanda(id_comanda);
        }

        public void createComanda(ComandaRequestDTO comanda) throws SQLException {
            comandaRepo.save(comanda);
            comanda.setFecha_hora(LocalDateTime.now());
        }
        public void deleteComanda(int id_comanda) throws SQLException {
            comandaRepo.delete(id_comanda);
        }

}



