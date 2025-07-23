package org.EdwarDa2.service;

import org.EdwarDa2.model.Categoria;
import org.EdwarDa2.model.Subcategoria;
import org.EdwarDa2.repository.CategoriaRepository;

import java.sql.SQLException;
import java.util.List;
public class CategoriaService {
    private final CategoriaRepository categoriaRepo;
    public CategoriaService(CategoriaRepository categoriaRepo) {
        this.categoriaRepo = categoriaRepo;
    }

    public List<Categoria> getCategorias() {
        return categoriaRepo.getAllCategorias();
    }

    public List<Subcategoria> getSubcategorias(int categoriaId) {
        return categoriaRepo.getSubcategoriasByCategoria(categoriaId);
    }

    public void createCategoria(Categoria categoria) throws SQLException {

        categoriaRepo.save(categoria);
    }
    public void updateCategoria(Categoria categoria) throws SQLException {
        categoriaRepo.update(categoria);
    }

    public void deleteCategoria(int id_categoria) throws SQLException {
        categoriaRepo.delete(id_categoria);
    }
    public Categoria fetchCategoriaById(int id) throws SQLException {
        return categoriaRepo.findById(id);  // Llama al repositorio para obtener la categoría
    }

}
