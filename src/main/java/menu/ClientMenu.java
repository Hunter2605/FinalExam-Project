package menu;

import service.ClientService;
import service.AuthenticationService;
import model.Repair;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Scanner;

public class ClientMenu {
    private Scanner scanner;
    private ClientService clientService;
    private AuthenticationService authService;
    private String username;

    public ClientMenu(Scanner scanner, String username) {
        this.scanner = scanner;
        this.username = username;
        this.clientService = new ClientService();
        this.authService = new AuthenticationService();
    }

    public void show() {
        System.out.println("\nПриветствую дорогой, Клиент!");

        while (true) {
            System.out.println("\nПожалуйста наберите номер меню для работы с программой, если закончили, то наберите 6:");
            System.out.println("1. Показать все услуги");
            System.out.println("2. Отдать на ремонт");
            System.out.println("3. Замена комплектующего");
            System.out.println("4. Обслуживание");
            System.out.println("5. Проверить статус");
            System.out.println("6. Выход");
            System.out.print(">>> ");

            int choice = Integer.parseInt(scanner.nextLine());

            switch (choice) {
                case 1:
                    showAllServices();
                    break;
                case 2:
                    submitForRepair();
                    break;
                case 3:
                    replacePart();
                    break;
                case 4:
                    performService();
                    break;
                case 5:
                    checkStatus();
                    break;
                case 6:
                    System.out.println("Программа завершена, мы будем рады вашему возвращению!");
                    return;
                default:
                    System.out.println("Неверный выбор, попробуйте еще раз.");
            }
        }
    }

    private void showAllServices() {
        System.out.println("\nСписок всех услуг мастерского:");
        System.out.println("1. Починить дисплей - 50$");
        System.out.println("2. Починить клавиатуру - 25$");
        System.out.println("3. Починить внутренности (Материнскую плату, процессор и т.д) - 40$");
        System.out.println("4. Замена батареи - 25$");
        System.out.println("5. Замена дисплея - 50$");
        System.out.println("6. Замена процессора - 100$");
        System.out.println("7. Замена материнской платы - 80$");
        System.out.println("8. Замена оперативной памяти - 30$");
        System.out.println("9. Чистка от пыли - 15$");
        System.out.println("10. Чистка от царапин - 20$");
    }

    private void submitForRepair() {
        System.out.println("\nПожалуйста выберите категорию техники для ремонта:");
        System.out.println("1. Починить дисплей - 50$");
        System.out.println("2. Починить клавиатуру - 25$");
        System.out.println("3. Починить внутренности (Материнскую плату, процессор и т.д) - 40$");
        System.out.print(">>> ");

        int repairType = Integer.parseInt(scanner.nextLine());
        String repairCategory = "";
        double repairCost = 0;
        int repairDays = 0;

        switch (repairType) {
            case 1:
                repairCategory = "Ремонт дисплея";
                repairCost = 50;
                repairDays = 3;
                break;
            case 2:
                repairCategory = "Ремонт клавиатуры";
                repairCost = 25;
                repairDays = 2;
                break;
            case 3:
                repairCategory = "Ремонт внутренних компонентов";
                repairCost = 40;
                repairDays = 5;
                break;
            default:
                System.out.println("Неверный выбор");
                return;
        }

        System.out.print("Введите тип техники (телефон): ");
        String deviceType = scanner.nextLine();

        System.out.print("Введите модель: ");
        String model = scanner.nextLine();

        System.out.print("Введите производителя: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Введите описание проблемы: ");
        String description = scanner.nextLine();

        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        int clientId = authService.getUserId(username);

        Repair repair = new Repair();
        repair.setDeviceType(deviceType);
        repair.setModel(model);
        repair.setManufacturer(manufacturer);
        repair.setDescription(description);
        repair.setQuantity(quantity);
        repair.setRepairCost(repairCost);
        repair.setRequiredMaterials(repairCategory);
        repair.setRepairDays(repairDays);
        repair.setClientId(clientId);

        clientService.submitForRepair(repair);

        System.out.println("Ваш заказ на ремонт принят.");
    }

    private void replacePart() {
        System.out.println("\nВыберите, что вы хотите заменить в своей технике:");
        System.out.println("1. Батарею - 25$");
        System.out.println("2. Дисплей - 50$");
        System.out.println("3. Процессор - 100$");
        System.out.println("4. Материнскую плату - 80$");
        System.out.println("5. Оперативную память - 30$");
        System.out.print(">>> ");

        int partChoice = Integer.parseInt(scanner.nextLine());
        String partName = "";
        double partPrice = 0;
        int repairDays = 0;

        switch (partChoice) {
            case 1:
                partName = "Батарея";
                partPrice = 25;
                repairDays = 1;
                break;
            case 2:
                partName = "Дисплей";
                partPrice = 50;
                repairDays = 2;
                break;
            case 3:
                partName = "Процессор";
                partPrice = 100;
                repairDays = 3;
                break;
            case 4:
                partName = "Материнская плата";
                partPrice = 80;
                repairDays = 4;
                break;
            case 5:
                partName = "Оперативная память";
                partPrice = 30;
                repairDays = 1;
                break;
            default:
                System.out.println("Неверный выбор");
                return;
        }

        System.out.print("Введите тип техники (телефон): ");
        String deviceType = scanner.nextLine();

        System.out.print("Введите модель: ");
        String model = scanner.nextLine();

        System.out.print("Введите производителя: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Введите описание: ");
        String description = "Замена " + partName;

        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        int clientId = authService.getUserId(username);

        Repair repair = new Repair();
        repair.setDeviceType(deviceType);
        repair.setModel(model);
        repair.setManufacturer(manufacturer);
        repair.setDescription(description);
        repair.setQuantity(quantity);
        repair.setRepairCost(partPrice);
        repair.setRequiredMaterials(partName);
        repair.setRepairDays(repairDays);
        repair.setClientId(clientId);

        clientService.submitForRepair(repair);

        System.out.println("Ваш заказ на замену принят. Ориентировочная дата завершения: " +
                LocalDate.now().plusDays(repairDays));
    }

    private void performService() {
        System.out.println("\nПожалуйста выберите тип обслуживания:");
        System.out.println("1. Чистка от пыли - 15$");
        System.out.println("2. Чистка от царапин - 20$");
        System.out.print(">>> ");

        int serviceType = Integer.parseInt(scanner.nextLine());
        String serviceName = "";
        double serviceCost = 0;
        int serviceDays = 0;

        switch (serviceType) {
            case 1:
                serviceName = "Чистка от пыли";
                serviceCost = 15;
                serviceDays = 1;
                break;
            case 2:
                serviceName = "Чистка от царапин";
                serviceCost = 20;
                serviceDays = 2;
                break;
            default:
                System.out.println("Неверный выбор");
                return;
        }

        System.out.print("Введите тип техники (телефон): ");
        String deviceType = scanner.nextLine();

        System.out.print("Введите модель: ");
        String model = scanner.nextLine();

        System.out.print("Введите производителя: ");
        String manufacturer = scanner.nextLine();

        System.out.print("Введите описание: ");
        String description = serviceName;

        System.out.print("Введите количество: ");
        int quantity = Integer.parseInt(scanner.nextLine());

        int clientId = authService.getUserId(username);

        Repair repair = new Repair();
        repair.setDeviceType(deviceType);
        repair.setModel(model);
        repair.setManufacturer(manufacturer);
        repair.setDescription(description);
        repair.setQuantity(quantity);
        repair.setRepairCost(serviceCost);
        repair.setRequiredMaterials(serviceName);
        repair.setRepairDays(serviceDays);
        repair.setClientId(clientId);

        clientService.submitForRepair(repair);

        System.out.println("Ваш заказ на обслуживание принят. Ориентировочная дата завершения: " +
                LocalDate.now().plusDays(serviceDays));
    }

    private void checkStatus() {
        System.out.print("Введите название модели или серийный номер вашей модели: ");
        String model = scanner.nextLine();

        int clientId = authService.getUserId(username);
        String status = clientService.checkRepairStatus(model, clientId);
        System.out.println(status);
    }
}