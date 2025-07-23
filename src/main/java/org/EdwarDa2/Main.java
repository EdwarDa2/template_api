package org.EdwarDa2;
import io.javalin.Javalin;
import io.javalin.plugin.bundled.CorsPluginConfig;
import org.EdwarDa2.di.AppModule;
public class Main {
    public static void main(String[] args) {

        Javalin app = Javalin.create(config -> {
            config.bundledPlugins.enableCors(cors -> {
                cors.addRule(CorsPluginConfig.CorsRule::anyHost);
            });
        }).start(7000);

        app.get("/", ctx -> ctx.result("API Javalin 2"));

        AppModule.initProductos().register(app);
        AppModule.initMesas().register(app);
        AppModule.initComandas().register(app);
        AppModule.initCategorias().register(app);
        AppModule.initMeseros().register(app);
        AppModule.initTotal().register(app);
        AppModule.initRol().register(app);
        AppModule.initAdmins().register(app);
        AppModule.initAvisos().register(app);
        AppModule.initStats().register(app);
    }
}