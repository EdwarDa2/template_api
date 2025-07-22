package org.EdwarDa2.service;

import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.repository.SubcategoriaRepository;

import java.sql.SQLException;
import java.util.List;

public class SubcategoriaServiceImpl implements ISubcategoriaService {

    private final SubcategoriaRepository repo;

    public SubcategoriaServiceImpl(SubcategoriaRepository repo) {
        this.repo = repo;
    }

    public List<Subcategoria> getAllSubcategoria() throws SQLException {
        return repo.findAll();
    }

    public Subcategoria getById_subcategoria(int id) throws SQLException {
        return repo.findById(id);
    }

    public void createSubcategoria(Subcategoria sub) throws SQLException {
        repo.save(sub);
    }

    public void updateSubcategoria(Subcategoria sub) throws SQLException {
        repo.update(sub);
    }

    public void deleteSubcategoria(int id) throws SQLException {
        repo.delete(id);
    }

    public List<Subcategoria> getByCategoria(int id_categoria) throws SQLException {
        return repo.findByCategoria(id_categoria);
    }
}
