package org.alilopez.service;

import org.alilopez.model.Comanda;
import org.alilopez.repository.ComandaRepository;
import java.sql.SQLException;
import java.util.List;
public class ComandaService {

        private final ComandaRepository comandaRepo;
        public ComandaService(ComandaRepository comandaRepo) {
            this.comandaRepo = comandaRepo;
        }
        public List<Comanda> getAllComanda() throws SQLException {
            return comandaRepo.findAll();
        }

        public Comanda getById_comanda(int id_comanda) throws SQLException {
            return comandaRepo.findById_comanda(id_comanda);
        }

        public void createComanda(Comanda comanda) throws SQLException {

            comandaRepo.save(comanda);
        }
        public void updateComanda(Comanda comanda) throws SQLException {
            comandaRepo.update(comanda);
        }

        public void deleteComanda(int id_comanda) throws SQLException {
            comandaRepo.delete(id_comanda);
        }


    }



