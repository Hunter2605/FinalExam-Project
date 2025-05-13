package menu;

import service.RepairmanService;
import java.util.Scanner;
import java.util.List;
import model.Repair;
import model.OrderedPart;

public class RepairmanMenu {
    private Scanner scanner;
    private RepairmanService repairmanService;

    public RepairmanMenu(Scanner scanner) {
        this.scanner = scanner;
        this.repairmanService = new RepairmanService();
    }

    public void show() {
        System.out.println("\nПриветствую дорогой, Ремонтник!");

        while (true) {
            System.out.println("\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 7:");
            System.out.println("1. Сделать ремонт");
            System.out.println("2. Сделать замену");
            System.out.println("3. Сделать обслуживание");
            System.out.println("4. Выполнить заказ запчастей");
            System.out.println("5. Посмотреть список заказанного оборудование");
            System.out.println("6. Удалить запчасть");
            System.out.println("7. Выход");
            System.out.print(">>> ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    performRepair();
                    break;
                case 2:
                    performReplacement();
                    break;
                case 3:
                    performService();
                    break;
                case 4:
                    orderParts();
                    break;
                case 5:
                    showOrderedParts();
                    break;
                case 6:
                    deleteOrderedPart();
                    break;
                case 7:
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        }
    }

    private void performRepair() {
        List<Repair> repairs = repairmanService.getPendingRepairs();
        if (repairs == null || repairs.isEmpty()) {
            System.out.println("Нет устройств, ожидающих ремонта.");
            return;
        }

        System.out.println("\nСписок техники для ремонта:");
        for (Repair repair : repairs) {
            System.out.println("ID: " + repair.getId() +
                    ", Модель: " + repair.getModel() +
                    ", Производитель: " + repair.getManufacturer() +
                    ", Описание: " + repair.getDescription());
        }

        System.out.print("\nВведите ID устройства, которое вы отремонтировали: ");
        int repairId = Integer.parseInt(scanner.nextLine());

        if (repairmanService.completeRepair(repairId)) {
            System.out.println("Ремонт успешно завершен.");
        } else {
            System.out.println("Ошибка при завершении ремонта.");
        }
    }

    private void performReplacement() {
        // Аналогичная реализация для замены детали
    }

    private void performService() {
        // Аналогичная реализация для обслуживания
    }

    private void orderParts() {
        // Реализация заказа запчастей
    }

    private void showOrderedParts() {
        List<OrderedPart> orderedParts = repairmanService.getOrderedParts();
        if (orderedParts == null || orderedParts.isEmpty()) {
            System.out.println("Нет заказанных запчастей.");
            return;
        }

        System.out.println("\nСписок заказанных запчастей:");
        for (OrderedPart part : orderedParts) {
            System.out.println("ID: " + part.getId() +
                    ", ID запчасти: " + part.getPartId() + ", Количество: " + part.getQuantity());
        }
    }

    private void deleteOrderedPart() {
        showOrderedParts();

        System.out.print("\nВведите ID заказа для удаления: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        if (repairmanService.deleteOrderedPart(orderId)) {
            System.out.println("Заказ успешно удален.");
        } else {
            System.out.println("Ошибка при удалении заказа.");
        }
    }
}