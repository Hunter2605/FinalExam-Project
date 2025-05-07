package service;

import dao.RepairDB;
import model.Repair;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class WorkerService {
    private RepairDB repairDAO;

    public WorkerService() {
        this.repairDAO = new RepairDB();
    }

    public List<Repair> getRepairsForRepair() {
        try {
            return repairDAO.getRepairsByType("Ремонт");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Repair> getRepairsForService() {
        try {
            return repairDAO.getRepairsByType("Обслуживание");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public List<Repair> getRepairsForReplacement() {
        try {
            return repairDAO.getRepairsByType("Замена");
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public Map<String, Integer> getRepairStatistics() {
        Map<String, Integer> stats = new HashMap<>();
        try {
            stats.put("Ремонт", repairDAO.getRepairCountByType("Ремонт"));
            stats.put("Обслуживание", repairDAO.getRepairCountByType("Обслуживание"));
            stats.put("Замена", repairDAO.getRepairCountByType("Замена"));
            return stats;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}