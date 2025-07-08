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

import org.alilopez.controller.ComandaController;
import org.alilopez.repository.ComandaRepository;
import org.alilopez.routes.ComandaRoutes;
import org.alilopez.service.ComandaService;

import org.alilopez.controller.CategoriaController;
import org.alilopez.repository.CategoriaRepository;
import org.alilopez.routes.CategoriaRoutes;
import org.alilopez.service.CategoriaService;

import org.alilopez.controller.MeseroController;
import org.alilopez.repository.MeseroRepository;
import org.alilopez.routes.MeseroRoutes;
import org.alilopez.service.MeseroService;

import org.alilopez.controller.TotalController;
import org.alilopez.repository.TotalRepository;
import org.alilopez.routes.TotalRoutes;
import org.alilopez.service.TotalService;

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
    public static TotalRoutes inittotal(){
        TotalRepository totalRepository = new TotalRepository();
        TotalService totalService = new TotalService(totalRepository);
        TotalController totalController = new TotalController(totalService);
        TotalRoutes totalRoutes = new TotalRoutes(totalController);
        return totalRoutes;

    }
}
