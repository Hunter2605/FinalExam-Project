package service;

import dao.PartDB;
import dao.RepairDB;
import dao.OrderedPartDB;
import model.Repair;
import model.OrderedPart;
import java.sql.SQLException;
import java.util.List;

public class RepairmanService {
    private RepairDB repairDAO;
    private PartDB partDAO;
    private OrderedPartDB orderedPartDAO;

    public RepairmanService() {
        this.repairDAO = new RepairDB();
        this.partDAO = new PartDB();
        this.orderedPartDAO = new OrderedPartDB();
    }

    public List<Repair> getPendingRepairs() {
        try {
            return repairDAO.getPendingRepairs();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean completeRepair(int repairId) {
        try {
            repairDAO.updateRepairStatus(repairId, "completed");
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean orderPart(int partId, int quantity) {
        try {
            OrderedPart orderedPart = new OrderedPart();
            orderedPart.setPartId(partId);
            orderedPart.setQuantity(quantity);
            orderedPart.setOrderDate(new java.sql.Date(System.currentTimeMillis()));
            orderedPart.setStatus("ordered");

            orderedPartDAO.addOrderedPart(orderedPart);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<OrderedPart> getOrderedParts() {
        try {
            return orderedPartDAO.getAllOrderedParts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deleteOrderedPart(int orderId) {
        try {
            orderedPartDAO.deleteOrderedPart(orderId);
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}