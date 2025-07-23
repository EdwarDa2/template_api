package org.EdwarDa2.service;

import org.EdwarDa2.DTO.AdminDTO;
import org.EdwarDa2.model.Admin;
import org.EdwarDa2.model.User;
import org.EdwarDa2.repository.AdminRepository;
import org.EdwarDa2.repository.UserRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private final AdminRepository adminRepo;
    private final UserRepository userRepo;

    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
        this.userRepo = new UserRepository();
    }

    public List<Admin> getAllAdmin() throws SQLException {
        return adminRepo.findAll();
    }

    public Admin getById_admin(int id_admin) throws SQLException {
        return adminRepo.findById_admin(id_admin);
    }

    public void createAdmin(AdminDTO dto) throws SQLException {
        // 1. Crear entidad User
        User user = new User(dto.getNombre(), dto.getApellido_p(), dto.getApellido_m(), dto.getRol());

        int idUser = userRepo.save(user);

        // 2. Crear Admin
        Admin admin = new Admin(idUser, dto.getClave());
        adminRepo.save(admin);
    }
    public void updateAdmin(Admin admin) throws SQLException {
        adminRepo.update(admin);
    }

    public void deleteAdmin(int id_admin) throws SQLException {
        adminRepo.delete(id_admin);
    }
}
