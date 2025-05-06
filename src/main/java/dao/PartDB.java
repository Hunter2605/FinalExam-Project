package dao;

import model.Part;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
public class PartDB {
    public List<Part> getAllParts() throws SQLException {
        List<Part> parts = new ArrayList<>();
        String sql = "SELECT * FROM parts";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                parts.add(new Part(
                        rs.getInt("id"),
                        rs.getString("name"),
                        rs.getInt("quantity"),
                        rs.getDouble("price")
                ));
            }
        }
        return parts;
    }

    public void updatePartQuantity(String partName, int quantity) throws SQLException {
        String sql = "UPDATE parts SET quantity = quantity + ? WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, quantity);
            stmt.setString(2, partName);
            stmt.executeUpdate();
        }
    }

    public Part getPartByName(String name) throws SQLException {
        String sql = "SELECT * FROM parts WHERE name = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

             stmt.setString(1, name);
                try (ResultSet rs = stmt.executeQuery()) {
                    if (rs.next()) {
                        return new Part(
                                rs.getInt("id"),
                                rs.getString("name"),
                                rs.getInt("quantity"),
                                rs.getDouble("price")
                        );
                    }
                }
        }
        return null;
    }
}
