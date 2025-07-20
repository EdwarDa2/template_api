package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Stat;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatsRepository {
    public List<Stat> createStatsAverage() throws SQLException {
        List<Stat> stats = new ArrayList<>();
        String query = "SELECT \n" +
                "name,\n" +
                "price,\n " +
                "amount,\n" +
                "    (price * amount) AS total_ingresos\n" +
                "FROM \n" +
                "    productos\n" +
                "ORDER BY \n" +
                "    total_ingresos DESC\n" +
                "LIMIT 5;";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Stat s = new Stat();
                s.setName(rs.getString("name"));
                s.setPrice(rs.getFloat("price"));
                s.setAmount(rs.getInt("amount"));
                stats.add(s);
            }
        }
        return stats;
    }
}
