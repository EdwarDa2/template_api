package org.EdwarDa2.di;
import org.EdwarDa2.controller.UserController;
import org.EdwarDa2.repository.UserRepository;
import org.EdwarDa2.routes.UserRoutes;
import org.EdwarDa2.service.UserService;

import org.EdwarDa2.repository.MesasRepository;
import org.EdwarDa2.routes.MesasRoutes;
import org.EdwarDa2.service.MesasService;
import org.EdwarDa2.controller.MesasController;

import org.EdwarDa2.controller.ProductoController;
import org.EdwarDa2.repository.ProductoRepository;
import org.EdwarDa2.routes.ProductoRoutes;
import org.EdwarDa2.service.ProductoService;

import org.EdwarDa2.controller.ComandaController;
import org.EdwarDa2.repository.ComandaRepository;
import org.EdwarDa2.routes.ComandaRoutes;
import org.EdwarDa2.service.ComandaService;

import org.EdwarDa2.controller.CategoriaController;
import org.EdwarDa2.repository.CategoriaRepository;
import org.EdwarDa2.routes.CategoriaRoutes;
import org.EdwarDa2.service.CategoriaService;

import org.EdwarDa2.controller.MeseroController;
import org.EdwarDa2.repository.MeseroRepository;
import org.EdwarDa2.routes.MeseroRoutes;
import org.EdwarDa2.service.MeseroService;

import org.EdwarDa2.controller.TotalController;
import org.EdwarDa2.repository.TotalRepository;
import org.EdwarDa2.routes.TotalRoutes;
import org.EdwarDa2.service.TotalService;

import org.EdwarDa2.controller.RolController;
import org.EdwarDa2.repository.RolRepository;
import org.EdwarDa2.routes.RolRoutes;
import org.EdwarDa2.service.RolService;


import org.EdwarDa2.controller.AdminController;
import org.EdwarDa2.repository.AdminRepository;
import org.EdwarDa2.routes.AdminRoutes;
import org.EdwarDa2.service.AdminService;

public class AppModule {
    public static UserRoutes initUser() {
        UserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);
        return new UserRoutes(userController);
    }

    public static ProductoRoutes initProductos() {
        ProductoRepository productoRepository = new ProductoRepository();
        ProductoService productoService = new ProductoService(productoRepository);
        ProductoController productoController = new ProductoController(productoService);
        ProductoRoutes productosRoutes = new ProductoRoutes(productoController);
        return productosRoutes;
    }
    public static MesasRoutes initMesas() {
        MesasRepository mesasRepository = new MesasRepository();
        MesasService mesasService = new MesasService(mesasRepository);
        MesasController mesasController = new MesasController(mesasService);
        MesasRoutes mesasRoutes = new MesasRoutes(mesasController);
        return mesasRoutes;

    }
    public static ComandaRoutes initComandas() {
        ComandaRepository comandaRepository = new ComandaRepository();
        ComandaService comandaService = new ComandaService(comandaRepository);
        ComandaController comandaController = new ComandaController(comandaService);
        ComandaRoutes comandaRoutes = new ComandaRoutes(comandaController);
        return comandaRoutes;
    }
    public static CategoriaRoutes initCategorias(){
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        CategoriaController categoriaController = new CategoriaController(categoriaService);
        CategoriaRoutes categoriaRoutes = new CategoriaRoutes(categoriaController);
        return categoriaRoutes;
    }
    public static MeseroRoutes initMeseros(){
        MeseroRepository meseroRepository = new MeseroRepository();
        MeseroService meseroService = new MeseroService(meseroRepository);
        MeseroController meseroController = new MeseroController(meseroService);
        MeseroRoutes meseroRoutes = new MeseroRoutes(meseroController);
        return meseroRoutes;

    }
    public static TotalRoutes initTotal(){
        TotalRepository totalRepository = new TotalRepository();
        TotalService totalService = new TotalService(totalRepository);
        TotalController totalController = new TotalController(totalService);
        TotalRoutes totalRoutes = new TotalRoutes(totalController);
        return totalRoutes;

    }
    public static RolRoutes initRol() {
        RolRepository rolRepository = new RolRepository();
        RolService rolService = new RolService(rolRepository);
        RolController rolController = new RolController(rolService);
        RolRoutes rolRoutes = new RolRoutes(rolController);
        return rolRoutes;
    }
    public static AdminRoutes initAdmins() {
        AdminRepository adminRepository = new AdminRepository();
        AdminService adminService = new AdminService(adminRepository);
        AdminController adminController = new AdminController(adminService);
        AdminRoutes adminRoutes = new AdminRoutes(adminController);
        return adminRoutes;
    }
}
