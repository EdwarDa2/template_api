package org.EdwarDa2;

import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.EdwarDa2.di.AppModule;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        }).start(7000);

        // Rutas generales
        app.get("/", ctx -> ctx.result("API Javalin 2"));

        AppModule.initUser().register(app);
        AppModule.initProductos().register(app);
        AppModule.initMesas().register(app);
        AppModule.initComandas().register(app);
        AppModule.initCategorias().register(app);
        AppModule.initMeseros().register(app);
        AppModule.initTotal().register(app);
        AppModule.initRol().register(app);
        AppModule.initAdmins().register(app);
    }
}