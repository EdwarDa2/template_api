package org.EdwarDa2.service;

import org.EdwarDa2.model.Admin;
import org.EdwarDa2.repository.AdminRepository;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private final AdminRepository adminRepo;
    public AdminService(AdminRepository adminRepo) {
        this.adminRepo = adminRepo;
    }
    public List<Admin> getAllAdmin() throws SQLException {
        return adminRepo.findAll();
    }

    public Admin getById_admin(int id_admin) throws SQLException {
        return adminRepo.findById_admin(id_admin);
    }

    public void createAdmin(Admin admin) throws SQLException {

        adminRepo.save(admin);
    }
    public void updateAdmin(Admin admin) throws SQLException {
        adminRepo.update(admin);
    }

    public void deleteAdmin(int id_admin) throws SQLException {
        adminRepo.delete(id_admin);
    }
}
