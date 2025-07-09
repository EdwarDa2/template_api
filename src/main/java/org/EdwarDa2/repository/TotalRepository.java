package org.EdwarDa2.repository;

import org.EdwarDa2.config.DatabaseConfig;
import org.EdwarDa2.model.Total;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TotalRepository {
    public List<Total> findAll() throws SQLException {
        List<Total> total = new ArrayList<>();
        String query = "SELECT * FROM total";
        try (
                Connection conn = DatabaseConfig.getDataSource().getConnection();
                PreparedStatement stmt = conn.prepareStatement(query);
                ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                Total t = new Total();
                t.setId_total(rs.getInt("id_total"));
                t.setPor_pagar(rs.getFloat("por_pagar"));
                total.add(t);
            }
        }
        return total;
    }

    public Total findById_total(int id_total) throws SQLException {
        Total total = null;
        String query = "SELECT * FROM total WHERE id_total = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {

            stmt.setInt(1, id_total);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    total = new Total();
                    total.setId_total(rs.getInt("id_total"));
                    total.setPor_pagar(rs.getFloat("por_pagar"));
                }
            }
        }
        return total;
    }
    public void save(Total total) throws SQLException {

        String query = "INSERT INTO total (por_pagar) VALUES (?)";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setFloat(1, total.getPor_pagar());
            stmt.executeUpdate();
        }
    }
    public void update(Total total) throws SQLException {
        String query = "UPDATE total  SET por_pagar = ? WHERE id_total = ?";
        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setFloat(1, total.getPor_pagar());
            stmt.setInt(2, total.getId_total());
            stmt.executeUpdate();
        }
    }
    public void delete(int id_total) throws SQLException {
        String query = "DELETE FROM total WHERE id_total = ?";

        try (Connection conn = DatabaseConfig.getDataSource().getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id_total);
            stmt.executeUpdate();
        }
    }
}
