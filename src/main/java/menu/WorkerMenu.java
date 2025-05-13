package menu;

import service.WorkerService;
import java.util.Scanner;
import java.util.List;
import java.util.Map;
import model.Repair;

public class WorkerMenu {
    private Scanner scanner;
    private WorkerService workerService;

    public WorkerMenu(Scanner scanner) {
        this.scanner = scanner;
        this.workerService = new WorkerService();
    }

    public void show() {
        System.out.println("\nПриветствую дорогой, Работник!");

        while (true) {
            System.out.println("\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 7:");
            System.out.println("1. Посмотреть список техник для ремонта");
            System.out.println("2. Посмотреть список техник для обслуживание");
            System.out.println("3. Посмотреть список техник для замены");
            System.out.println("4. Показать самый большой заказ");
            System.out.println("5. Показать самый маленький заказ");
            System.out.println("6. Посмотреть статистику");
            System.out.println("7. Выход");
            System.out.print(">>> ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showRepairList();
                    break;
                case 2:
                    showServiceList();
                    break;
                case 3:
                    showReplacementList();
                    break;
                case 4:
                    showLargestOrder();
                    break;
                case 5:
                    showSmallestOrder();
                    break;
                case 6:
                    showStatistics();
                    break;
                case 7:
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        }
    }

    private void showRepairList() {
        List<Repair> repairs = workerService.getRepairsForRepair();
        if (repairs == null || repairs.isEmpty()) {
            System.out.println("Нет устройств, ожидающих ремонта.");
            return;
        }

        System.out.println("\nСписок техники для ремонта:");
        for (Repair repair : repairs) {
            System.out.println("Модель: " + repair.getModel() +
                    ", Производитель: " + repair.getManufacturer() +
                    ", Описание: " + repair.getDescription());
        }
    }

    private void showServiceList() {
        List<Repair> services = workerService.getRepairsForService();
        if (services == null || services.isEmpty()) {
            System.out.println("Нет устройств, ожидающих обслуживания.");
            return;
        }

        System.out.println("\nСписок техники для обслуживания:");
        for (Repair service : services) {
            System.out.println("Модель: " + service.getModel() +
                    ", Производитель: " + service.getManufacturer() +
                    ", Описание: " + service.getDescription());
        }
    }

    private void showReplacementList() {
        List<Repair> replacements = workerService.getRepairsForReplacement();
        if (replacements == null || replacements.isEmpty()) {
            System.out.println("Нет устройств, ожидающих замены.");
            return;
        }

        System.out.println("\nСписок техники для замены:");
        for (Repair replacement : replacements) {
            System.out.println("Модель: " + replacement.getModel() +
                    ", Производитель: " + replacement.getManufacturer() + ", Описание: " + replacement.getDescription());
        }
    }

    private void showLargestOrder() {
        // Реализация показа самого большого заказа
    }

    private void showSmallestOrder() {
        // Реализация показа самого маленького заказа
    }

    private void showStatistics() {
        Map<String, Integer> stats = workerService.getRepairStatistics();
        if (stats != null) {
            System.out.println("\nСтатистика по ремонтам:");
            for (Map.Entry<String, Integer> entry : stats.entrySet()) {
                System.out.println(entry.getKey() + ": " + entry.getValue());
            }
        } else {
            System.out.println("Не удалось получить статистику.");
        }
    }
}