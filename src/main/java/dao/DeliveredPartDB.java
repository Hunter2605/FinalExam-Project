package dao;

import model.DeliveredPart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveredPartDB {
    public void addDeliveredPart(DeliveredPart deliveredPart) throws SQLException {
        String sql = "INSERT INTO delivered_parts (part_id, quantity, delivery_date, supplier_id) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, deliveredPart.getPartId());
            stmt.setInt(2, deliveredPart.getQuantity());
            stmt.setDate(3, deliveredPart.getDeliveryDate());
            stmt.setInt(4, deliveredPart.getSupplierId());

            stmt.executeUpdate();
        }
    }

    public List<DeliveredPart> getAllDeliveredParts() throws SQLException {
        List<DeliveredPart> deliveredParts = new ArrayList<>();
        String sql = "SELECT * FROM delivered_parts";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                DeliveredPart deliveredPart = new DeliveredPart();
                deliveredPart.setId(rs.getInt("id"));
                deliveredPart.setPartId(rs.getInt("part_id"));
                deliveredPart.setQuantity(rs.getInt("quantity"));
                deliveredPart.setDeliveryDate(rs.getDate("delivery_date"));
                deliveredPart.setSupplierId(rs.getInt("supplier_id"));

                deliveredParts.add(deliveredPart);
            }
        }
        return deliveredParts;
    }
}