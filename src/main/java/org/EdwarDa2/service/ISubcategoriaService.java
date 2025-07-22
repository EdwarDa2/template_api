package org.EdwarDa2.service;

import org.EdwarDa2.model.Subcategoria;

import java.sql.SQLException;
import java.util.List;

public interface ISubcategoriaService {
    List<Subcategoria> getAllSubcategoria() throws SQLException;
    Subcategoria getById_subcategoria(int id) throws SQLException;
    void createSubcategoria(Subcategoria sub) throws SQLException;
    void updateSubcategoria(Subcategoria sub) throws SQLException;
    void deleteSubcategoria(int id) throws SQLException;
    List<Subcategoria> getByCategoria(int id_categoria) throws SQLException;
}
