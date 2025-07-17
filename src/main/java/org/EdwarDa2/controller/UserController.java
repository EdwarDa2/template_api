package org.EdwarDa2.controller;
import io.javalin.http.Context;
import io.javalin.http.HttpStatus;
import org.EdwarDa2.DTO.MeseroDTO;
import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.User;
import org.EdwarDa2.service.UserService;


import java.sql.*;
import java.util.List;

public class UserController {
    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    public void getAll(Context ctx) {
        try {
            List<User> usuario = userService.getAllUsuario();
            ctx.json(usuario);
        } catch (SQLException e) {
            ctx.status(500).result("Error al obtener usuarios");
        }
    }

    public void getById(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id_usuario"));
            User user = userService.getByIdUser(id);
            if (user != null) {
                ctx.json(user);
            } else {
                ctx.status(HttpStatus.NOT_FOUND).result("Usuario no encontrado");
            }
        } catch (Exception e) {
            ctx.status(404).result("Error al obtener usuarios");
        }
    }

    public void create(Context ctx) {
        try {
            User user = ctx.bodyAsClass(User.class);
            userService.createUser(user);
            ctx.status(201).result("Usuario creado");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(400).result("Error al crear usuario");
        }
    }
    public void update(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            User user = ctx.bodyAsClass(User.class);
            if (user.getId_usuario() != id) {
                ctx.status(400).result("ID en la URL no coincide con el ID en el cuerpo de la solicitud.");
                return;
            }
            userService.updateUser(user);
            ctx.status(200).result("Usuario actualizado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al actualizar usuario: " + e.getMessage());
        }
    }
    public void delete(Context ctx) {
        try {
            int id = Integer.parseInt(ctx.pathParam("id"));
            userService.deleteUser(id);
            ctx.status(204).result("Usuario eliminado exitosamente");
        } catch (NumberFormatException e) {
            ctx.status(400).result("ID de usuario inválido.");
        } catch (Exception e) {
            e.printStackTrace();
            ctx.status(500).result("Error al eliminar usuario: " + e.getMessage());
        }
    }

    public void getMeseroWithUser(Context ctx) {
        String sql = "SELECT u.*, m.* FROM usuario u " +
                "INNER JOIN meseros m ON u.idUsuario = m.idUsuario " +
                "WHERE m.idMesero = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, Integer.parseInt(ctx.pathParam("id_mesero")));
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {

                MeseroDTO meseroDTO = new MeseroDTO(rs.getInt("u.id_usuario"),
                        rs.getString("u.nombre"),
                        rs.getInt("m.clave"),
                        rs.getInt("m.id_mesero")

                );
                ctx.json(meseroDTO);
            } else {
                ctx.status(404).result("Mesero no encontrado");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Error en la base de datos");
        }
    }

    public void createMesero(Context ctx) {

        MeseroDTO meseroDTO = ctx.bodyAsClass(MeseroDTO.class);

        String insertUsuarioSQL = "INSERT INTO usuario (nombre) VALUES (? )";
        String insertMeseroSQL = "INSERT INTO meseros (id_usuario,clave, id_mesero) VALUES (?, ?, ?)";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection()) {
            conn.setAutoCommit(false);


            try (PreparedStatement stmtUsuario = conn.prepareStatement(insertUsuarioSQL, Statement.RETURN_GENERATED_KEYS)) {
                stmtUsuario.setString(1, meseroDTO.getNombre());
                int affectedRows = stmtUsuario.executeUpdate();

                if (affectedRows > 0) {
                    ResultSet generatedKeys = stmtUsuario.getGeneratedKeys();
                    if (generatedKeys.next()) {
                        int id_usuario = generatedKeys.getInt(1);


                        try (PreparedStatement stmtMesero = conn.prepareStatement(insertMeseroSQL)) {
                            stmtMesero.setInt(1, id_usuario);
                            stmtMesero.setInt(2, meseroDTO.getClave());
                            stmtMesero.setInt(3, meseroDTO.getId_mesero());
                            stmtMesero.executeUpdate();

                            conn.commit();
                            ctx.status(201).result("Mesero creado exitosamente");
                        }
                    }
                }
            } catch (SQLException e) {
                conn.rollback();
                ctx.status(500).result("Error al crear mesero");
            }

        } catch (SQLException e) {
            e.printStackTrace();
            ctx.status(500).result("Error en la base de datos");
        }
    }


}







