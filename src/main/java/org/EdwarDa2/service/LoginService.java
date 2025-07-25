package org.EdwarDa2.service;

import org.EdwarDa2.model.Usuario;
import org.EdwarDa2.repository.AdminRepository;
import org.EdwarDa2.repository.MeseroRepository;

public class LoginService {

    private final AdminRepository adminRepository;
    private final MeseroRepository meseroRepository;

    public LoginService(AdminRepository adminRepository, MeseroRepository meseroRepository) {
        this.adminRepository = adminRepository;
        this.meseroRepository = meseroRepository;
    }

    public Usuario validarPorClave(String clave) {
        Usuario usuario = adminRepository.validarAdmin(clave);
        if (usuario != null) {
            return usuario;
        }

        usuario = meseroRepository.validarMesero(clave);
        if (usuario != null) {
            return usuario;
        }

        return null;
    }
}
