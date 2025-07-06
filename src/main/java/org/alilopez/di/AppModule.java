package org.alilopez.di;
import org.alilopez.controller.UserController;
import org.alilopez.repository.UserRepository;
import org.alilopez.routes.UserRoutes;
import org.alilopez.service.UserService;

import org.alilopez.repository.MesasRepository;
import org.alilopez.routes.MesasRoutes;
import org.alilopez.service.MesasService;
import org.alilopez.controller.MesasController;

import org.alilopez.controller.ProductoController;
import org.alilopez.repository.ProductoRepository;
import org.alilopez.routes.ProductoRoutes;
import org.alilopez.service.ProductoService;


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
}
