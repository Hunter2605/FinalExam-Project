package service;

import dao.DeliveredPartDB;
import dao.OrderedPartDB;
import dao.PartDB;
import model.DeliveredPart;
import model.OrderedPart;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

public class SupplierService {
    private OrderedPartDB orderedPartDB;
    private DeliveredPartDB deliveredPartDB;
    private PartDB partDB;

    public SupplierService() {
        this.orderedPartDB = new OrderedPartDB();
        this.deliveredPartDB = new DeliveredPartDB();
        this.partDB = new PartDB();
    }

    public List<OrderedPart> getOrderedParts() {
        try {
            return orderedPartDB.getAllOrderedParts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }

    public boolean deliverPart(int orderId, int supplierId) {
        try {
            OrderedPart orderedPart = orderedPartDB.getOrderedPartById(orderId);
            if (orderedPart != null) {
                DeliveredPart deliveredPart = new DeliveredPart();
                deliveredPart.setPartId(orderedPart.getPartId());
                deliveredPart.setQuantity(orderedPart.getQuantity());
                deliveredPart.setDeliveryDate(new Date(System.currentTimeMillis()));
                deliveredPart.setSupplierId(supplierId);

                deliveredPartDB.addDeliveredPart(deliveredPart);

                partDB.updatePartQuantity(orderedPart.getPartId(), orderedPart.getQuantity());

                orderedPartDB.deleteOrderedPart(orderId);

                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public List<DeliveredPart> getDeliveredParts() {
        try {
            return deliveredPartDB.getAllDeliveredParts();
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
}