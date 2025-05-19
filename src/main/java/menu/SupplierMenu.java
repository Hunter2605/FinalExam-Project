package menu;

import service.SupplierService;
import java.util.Scanner;
import java.util.List;
import model.OrderedPart;
import model.DeliveredPart;

public class SupplierMenu {
    private Scanner scanner;
    private SupplierService supplierService;
    private String username;

    public SupplierMenu(Scanner scanner, String username) {
        this.scanner = scanner;
        this.username = username;
        this.supplierService = new SupplierService();
    }

    public void show() {
        System.out.println("\nПриветствую дорогой, Поставщик!");

        while (true) {
            System.out.println("\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 4:");
            System.out.println("1. Показать весь список заказанных запчастей");
            System.out.println("2. Доставить материал");
            System.out.println("3. Показать доставленные материалы");
            System.out.println("4. Выход");
            System.out.print(">>> ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showOrderedParts();
                    break;
                case 2:
                    deliverParts();
                    break;
                case 3:
                    showDeliveredParts();
                    break;
                case 4:
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        }
    }

    private void showOrderedParts() {
        List<OrderedPart> orderedParts = supplierService.getOrderedParts();
        if (orderedParts == null || orderedParts.isEmpty()) {
            System.out.println("Нет заказанных запчастей.");
            return;
        }

        System.out.println("\nСписок заказанных запчастей:");
        for (OrderedPart part : orderedParts) {
            System.out.println("ID: " + part.getId() +
                    ", ID запчасти: " + part.getPartId() +
                    ", Количество: " + part.getQuantity());
        }
    }

    private void deliverParts() {
        showOrderedParts();

        System.out.print("\nВведите ID заказа для доставки: ");
        int orderId = Integer.parseInt(scanner.nextLine());

        int supplierId = 1;

        if (supplierService.deliverPart(orderId, supplierId)) {
            System.out.println("Запчасть успешно доставлена.");
        } else {
            System.out.println("Ошибка при доставке запчасти.");
        }
    }

    private void showDeliveredParts() {
        List<DeliveredPart> deliveredParts = supplierService.getDeliveredParts();
        if (deliveredParts == null || deliveredParts.isEmpty()) {
            System.out.println("Нет доставленных запчастей.");
            return;
        }

        System.out.println("\nСписок доставленных запчастей:");
        for (DeliveredPart part : deliveredParts) {
            System.out.println("ID: " + part.getId() +
                    ", ID запчасти: " + part.getPartId() +
                    ", Количество: " + part.getQuantity());
        }
    }
}