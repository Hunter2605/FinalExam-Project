package dao;

import model.OrderedPart;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OrderedPartDB {
    public void addOrderedPart(OrderedPart orderedPart) throws SQLException {
        String sql = "INSERT INTO ordered_parts (part_id, quantity, order_date, status) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderedPart.getPartId());
            stmt.setInt(2, orderedPart.getQuantity());
            stmt.setDate(3, orderedPart.getOrderDate());
            stmt.setString(4, orderedPart.getStatus());

            stmt.executeUpdate();
        }
    }

    public List<OrderedPart> getAllOrderedParts() throws SQLException {
        List<OrderedPart> orderedParts = new ArrayList<>();
        String sql = "SELECT * FROM ordered_parts WHERE status = 'ordered'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                OrderedPart orderedPart = new OrderedPart();
                orderedPart.setId(rs.getInt("id"));
                orderedPart.setPartId(rs.getInt("part_id"));
                orderedPart.setQuantity(rs.getInt("quantity"));
                orderedPart.setOrderDate(rs.getDate("order_date"));
                orderedPart.setStatus(rs.getString("status"));

                orderedParts.add(orderedPart);
            }
        }
        return orderedParts;
    }

    public void deleteOrderedPart(int orderId) throws SQLException {
        String sql = "DELETE FROM ordered_parts WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, orderId);
            stmt.executeUpdate();
        }
    }
}