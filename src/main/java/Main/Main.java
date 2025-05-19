package Main;
import menu.ClientMenu;
import menu.RepairmanMenu;
import menu.WorkerMenu;
import menu.SupplierMenu;
import service.AuthenticationService;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws ClassNotFoundException {
        Scanner scanner = new Scanner(System.in);
        AuthenticationService authService = new AuthenticationService();

        while (true) {
            System.out.println("Для запуска программы, пожалуйста введите тип аккаунта:");
            System.out.print(">>> ");
            String accountType = scanner.nextLine().toLowerCase();

            if (!accountType.equals("client") &&
                    !accountType.equals("repair") &&
                    !accountType.equals("worker") &&
                    !accountType.equals("supplier")) {
                System.out.println("Извините, но мы не нашли такой тип аккаунта, пожалуйста повторите.");
                continue;
            }


            System.out.print("Введите логин: ");
            String username = scanner.nextLine();

            System.out.print("Введите пароль: ");
            String password = scanner.nextLine();

            if (authService.authenticate(username, password) != null) {
                switch (accountType.toLowerCase()) {
                    case "client":
                        new ClientMenu(scanner, username).show();
                        break;
                    case "repair":
                        new RepairmanMenu(scanner).show();
                        break;
                    case "worker":
                        new WorkerMenu(scanner).show();
                        break;
                    case "supplier":
                        new SupplierMenu(scanner, username).show();
                        break;
                    default:
                        System.out.println("Неверный тип аккаунта.");
                }
            } else {
                System.out.println("Неверный логин или пароль.");
            }
        }
    }
}