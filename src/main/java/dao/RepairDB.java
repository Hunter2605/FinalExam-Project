package dao;

import model.Repair;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RepairDB {
    public void addRepair(Repair repair) throws SQLException {
        String sql = "INSERT INTO repairs (device_type, model, manufacturer, description, quantity, " +
                "repair_cost, required_materials, repair_days, client_id, status, start_date, end_date) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, repair.getDeviceType());
            stmt.setString(2, repair.getModel());
            stmt.setString(3, repair.getManufacturer());
            stmt.setString(4, repair.getDescription());
            stmt.setInt(5, repair.getQuantity());
            stmt.setDouble(6, repair.getRepairCost());
            stmt.setString(7, repair.getRequiredMaterials());
            stmt.setInt(8, repair.getRepairDays());
            stmt.setInt(9, repair.getClientId());
            stmt.setString(10, repair.getStatus());
            stmt.setDate(11, repair.getStartDate());
            stmt.setDate(12, repair.getEndDate());

            stmt.executeUpdate();
        }
    }

    public List<Repair> getPendingRepairs() throws SQLException {
        List<Repair> repairs = new ArrayList<>();
        String sql = "SELECT * FROM repairs WHERE status = 'pending'";

        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Repair repair = new Repair();
                repair.setId(rs.getInt("id"));
                repair.setDeviceType(rs.getString("device_type"));
                repair.setModel(rs.getString("model"));
                repair.setManufacturer(rs.getString("manufacturer"));
                repair.setDescription(rs.getString("description"));
                repair.setQuantity(rs.getInt("quantity"));
                repair.setRepairCost(rs.getDouble("repair_cost"));
                repair.setRequiredMaterials(rs.getString("required_materials"));
                repair.setRepairDays(rs.getInt("repair_days"));
                repair.setClientId(rs.getInt("client_id"));
                repair.setStatus(rs.getString("status"));
                repair.setStartDate(rs.getDate("start_date"));
                repair.setEndDate(rs.getDate("end_date"));

                repairs.add(repair);
            }
        }
        return repairs;
    }

    public void updateRepairStatus(int repairId, String status) throws SQLException {
        String sql = "UPDATE repairs SET status = ? WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, status);
            stmt.setInt(2, repairId);
            stmt.executeUpdate();
        }
    }

    public Repair getRepairById(int repairId) throws SQLException {
        String sql = "SELECT * FROM repairs WHERE id = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, repairId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Repair repair = new Repair();
                    repair.setId(rs.getInt("id"));
                    repair.setDeviceType(rs.getString("device_type"));
                    repair.setModel(rs.getString("model"));
                    repair.setManufacturer(rs.getString("manufacturer"));
                    repair.setDescription(rs.getString("description"));
                    repair.setQuantity(rs.getInt("quantity"));
                    repair.setRepairCost(rs.getDouble("repair_cost"));
                    repair.setRequiredMaterials(rs.getString("required_materials"));
                    repair.setRepairDays(rs.getInt("repair_days"));
                    repair.setClientId(rs.getInt("client_id"));
                    repair.setStatus(rs.getString("status"));
                    repair.setStartDate(rs.getDate("start_date"));
                    repair.setEndDate(rs.getDate("end_date"));

                    return repair;
                }
            }
        }
        return null;
    }
    public List<Repair> getRepairsByType(String type) throws SQLException {
        List<Repair> repairs = new ArrayList<>();
        String sql = "SELECT * FROM repairs WHERE description LIKE ? AND status = 'pending' ";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + type + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    Repair repair = new Repair();
                    repair.setId(rs.getInt("id"));
                    repair.setDeviceType(rs.getString("device_type"));
                    repair.setModel(rs.getString("model"));
                    repair.setManufacturer(rs.getString("manufacturer"));
                    repair.setDescription(rs.getString("description"));
                    repair.setQuantity(rs.getInt("quantity"));
                    repair.setRepairCost(rs.getDouble("repair_cost"));
                    repair.setRequiredMaterials(rs.getString("required_materials"));
                    repair.setRepairDays(rs.getInt("repair_days"));
                    repair.setClientId(rs.getInt("client_id"));
                    repair.setStatus(rs.getString("status"));
                    repair.setStartDate(rs.getDate("start_date"));
                    repair.setEndDate(rs.getDate("end_date"));
                    repairs.add(repair);
                }
            }
        }
        return repairs;
    }
    public int getRepairCountByType(String type) throws SQLException {
        String sql = "SELECT COUNT(*) FROM repairs WHERE description LIKE ?";
        try (Connection conn = DatabaseConnection.getConnection();
        PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, "%" + type + "%");
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getInt(1);
                }
            }
        }
        return 0;
    }
    public Repair getRepairByModelAndClient(String model, int clientId) throws SQLException {
        String sql = "SELECT * FROM repairs WHERE model = ? AND client_id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, model);
            stmt.setInt(2, clientId);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Repair repair = new Repair();
                    repair.setId(rs.getInt("id"));
                    repair.setDeviceType(rs.getString("device_type"));
                    repair.setModel(rs.getString("model"));
                    repair.setManufacturer(rs.getString("manufacturer"));
                    repair.setDescription(rs.getString("description"));
                    repair.setQuantity(rs.getInt("quantity"));
                    repair.setRepairCost(rs.getDouble("repair_cost"));
                    repair.setRequiredMaterials(rs.getString("required_materials"));
                    repair.setRepairDays(rs.getInt("repair_days"));
                    repair.setClientId(rs.getInt("client_id"));
                    repair.setStatus(rs.getString("status"));
                    repair.setStartDate(rs.getDate("start_date"));
                    repair.setEndDate(rs.getDate("end_date"));

                    return repair;
                }
            }
        }
        return null;
    }

}
