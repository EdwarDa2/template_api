package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class StatsRepository {
    public List<Map<String, Object>> getGananciaPorMesa(String fechaInicio, String fechaFin) throws SQLException {
        List<Map<String, Object>> lista = new ArrayList<>();

        StringBuilder sql = new StringBuilder("""
        SELECT c.id_mesa,
               SUM(p.precio * d.cantidad) AS total_ganancia
        FROM comandas c
        JOIN detallecomanda d ON c.id_comanda = d.id_comanda
        JOIN productos p ON d.id_producto = p.id_producto
    """);

        List<Object> parametros = new ArrayList<>();
        if (fechaInicio != null && fechaFin != null) {
            sql.append("WHERE DATE(c.fecha_hora) BETWEEN ? AND ? ");
            parametros.add(fechaInicio);
            parametros.add(fechaFin);
        }

        sql.append("GROUP BY c.id_mesa ORDER BY total_ganancia DESC");

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql.toString())) {

            for (int i = 0; i < parametros.size(); i++) {
                stmt.setObject(i + 1, parametros.get(i));
            }

            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                Map<String, Object> mesa = new HashMap<>();
                mesa.put("id_mesa", rs.getInt("id_mesa"));
                mesa.put("total_ganancia", rs.getDouble("total_ganancia"));
                lista.add(mesa);
            }
        }

        return lista;
    }



}