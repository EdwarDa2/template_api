package org.EdwarDa2.service;
import at.favre.lib.crypto.bcrypt.BCrypt;
import org.EdwarDa2.model.User;
import org.EdwarDa2.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;


public class UserService {
    private final UserRepository userRepo;
    public UserService(UserRepository userRepo) {
        this.userRepo = userRepo;
    }
    public List<User> getAllUsuario() throws SQLException {
        return userRepo.findAll();
    }
    public User getByIdUser(int id_usuario) throws SQLException {
        return userRepo.findByIdUser(id_usuario);
    }

    public void createUser(User user) throws SQLException {
        //String hashedContrasena = BCrypt.withDefaults().hashToString(12, user.getClave().toCharArray());
        //user.setClave(hashedContrasena);
        //userRepo.save(user);
    }

    public void updateUser(User user) throws SQLException {
        userRepo.update(user);
    }

    public void deleteUser(int idUser) throws SQLException {
        userRepo.delete(idUser);
    }
}






