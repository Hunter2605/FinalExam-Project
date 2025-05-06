package service;

import dao.PartDB;
import dao.RepairDB;
import model.Part;
import model.Repair;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Map;
import java.util.HashMap;
import java.sql.SQLException;

public class ClientService {
    private RepairDB repairDB;
    private PartDB partDB;
    private Map<String, Double> brandCoefficients;

    public ClientService() {
        this.repairDB = new RepairDB();
        this.partDB = new PartDB();

        this.brandCoefficients = new HashMap<>();
        brandCoefficients.put("Apple", 3.0);
        brandCoefficients.put("Samsung", 2.5);
        brandCoefficients.put("Xiomi", 1.8);
        brandCoefficients.put("Huawei", 2.3);
    }

    public void submitForRepair(Repair repair) {
        try {
            double coefficient = brandCoefficients.getOrDefault(repair.getManufacturer(), 1.0);
            repair.setRepairCost(repair.getRepairCost() * coefficient);

            repair.setStartDate(Date.valueOf(LocalDate.now()));
            repair.setEndDate(Date.valueOf(LocalDate.now().plusDays(repair.getRepairDays())));
            repair.setStatus("pending");

            repairDB.addRepair(repair);

            if (repair.getDescription().startsWith("Замена")) {
                String partName = repair.getRequiredMaterials();
                checkAndOrderPart(partName, repair.getQuantity());
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void checkAndOrderPart(String partName, int quantity) throws SQLException {
        Part part = partDB.getPartByName(partName);
        if (part == null || part.getQuantity() < quantity) {
            int orderQuantity = part == null ? quantity : quantity - part.getQuantity();
        }
    }

    public String checkRepairStatus(String model, int clientId) {
        try {
            Repair repair = repairDB.getRepairByModelAndClient(model, clientId);
            if (repair != null) {
                return repair.getStatus().equals("completed") ?
                        "Ваша техника готова!" :
                        "Ваша техника еще не готова. Ориентировочная дата завершения: " + repair.getEndDate();
            }
            return "Техника с таким названием или номером не найдена.";
        } catch (SQLException e) {
            e.printStackTrace();
            return "Ошибка при проверке статуса ремонта.";
        }
    }
}