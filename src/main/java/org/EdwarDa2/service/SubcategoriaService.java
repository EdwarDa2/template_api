package org.EdwarDa2.service;

import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.repository.SubcategoriaRepository;



import java.sql.SQLException;
import java.util.List;

public class SubcategoriaService {
    private final SubcategoriaRepository subcategoriaRepo;
    public SubcategoriaService(SubcategoriaRepository subcategoriaRepo) {
        this.subcategoriaRepo = subcategoriaRepo;
    }

    public List<Subcategoria> getAllSubcategoria() throws SQLException {
        return subcategoriaRepo.findAll();
    }

    public Subcategoria getById_subcategoria(int id_subcategoria) throws SQLException {
        return subcategoriaRepo.findById(id_subcategoria);
    }

    public void createSubcategoria(Subcategoria subcategoria) throws SQLException {

        subcategoriaRepo.save(subcategoria);
    }
    public void updateSubcategoria(Subcategoria subcategoria) throws SQLException {
        subcategoriaRepo.update(subcategoria);
    }

    public void deleteSubcategoria(int id_subcategoria) throws SQLException {
        subcategoriaRepo.delete(id_subcategoria);
    }
    public Subcategoria fetchSubcategoriaById(int id) throws SQLException {
        return subcategoriaRepo.findById(id);
    }

}
