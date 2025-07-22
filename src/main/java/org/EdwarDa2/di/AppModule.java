package org.EdwarDa2.di;
import io.javalin.Javalin;
import org.EdwarDa2.controller.*;
import org.EdwarDa2.repository.*;
import org.EdwarDa2.routes.*;
import org.EdwarDa2.service.*;

import static java.rmi.Naming.bind;

public class AppModule {
    public static UserRoutes initUser() {
        UserRepository userRepo = new UserRepository();
        UserService userService = new UserService(userRepo);
        UserController userController = new UserController(userService);
        return new UserRoutes(userController);
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

    public static CategoriaRoutes initCategorias() {
        CategoriaRepository categoriaRepository = new CategoriaRepository();
        CategoriaService categoriaService = new CategoriaService(categoriaRepository);
        CategoriaController categoriaController = new CategoriaController(categoriaService);
        CategoriaRoutes categoriaRoutes = new CategoriaRoutes(categoriaController);
        return categoriaRoutes;
    }

    public static MeseroRoutes initMeseros() {
        MeseroRepository meseroRepository = new MeseroRepository();
        MeseroService meseroService = new MeseroService(meseroRepository);
        MeseroController meseroController = new MeseroController(meseroService);
        MeseroRoutes meseroRoutes = new MeseroRoutes(meseroController);
        return meseroRoutes;

    }

    public static TotalRoutes initTotal() {
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

    public static AvisosRoutes initAvisos() {
        AvisosRepository avisosRepository = new AvisosRepository();
        AvisosService avisosService = new AvisosService(avisosRepository);
        AvisosController avisosController = new AvisosController(avisosService);
        AvisosRoutes avisosRoutes = new AvisosRoutes(avisosController);
        return avisosRoutes;
    }

    public static StatsRoutes initStats() {
        StatsRepository statsRepository = new StatsRepository();
        StatsService statsService = new StatsService(statsRepository);
        StatsController statsController = new StatsController(statsService);
        StatsRoutes statsRoutes = new StatsRoutes(statsController);
        return statsRoutes;
    }

        public static ProductoRoutes initProductos() {
            ProductoRepository productoRepository = new ProductoRepository();
            ProductoService productoService = new ProductoService(productoRepository);
            ProductoController productoController = new ProductoController(productoService);
            ProductoRoutes productoRoutes = new ProductoRoutes(productoController);
            return productoRoutes;
        }
    }

