package apps;

import controllers.IUserController;
import controllers.ITransactionController;
import services.AuthorizationService;

import java.util.Map;
import java.util.Scanner;

public class AuthApplication {
    private final IUserController userController;
    private final ITransactionController transactionController;
    private final Scanner scanner = new Scanner(System.in);
    private boolean running = true;

    public AuthApplication(IUserController userController, ITransactionController transactionController) {
        this.userController = userController;
        this.transactionController = transactionController;
    }

    public void start() {
        Map<Integer, Runnable> menuActions = Map.of(
                1, this::loginAsUser,
                2, this::registerAsUser,
                3, this::enterAsAdmin
        );

        while (running) {
            mainMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                menuActions.getOrDefault(option, this::invalidOption).run();
            } catch (Exception e) {
                System.out.println(e);
                invalidOption();
            }
        }
    }

    private void loginAsUser() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();

        try {
            boolean status = AuthorizationService.getInstance().loginUser(username, password);
            if (status) {
                System.out.println("Logged in");
                new UserApplication(userController, transactionController).start();
                this.running = false;
            } else {
                System.out.println("Invalid username or password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void registerAsUser() {
        System.out.println("Enter username: ");
        String username = scanner.nextLine();
        System.out.println("Enter password: ");
        String password = scanner.nextLine();
        System.out.println("Enter currency: ");
        String currency = scanner.nextLine();

        try {
            boolean status = AuthorizationService.getInstance().registerUser(username, password, currency);
            if (status) {
                System.out.println("Registered");
                new UserApplication(userController, transactionController).start();
                this.running = false;
            } else {
                System.out.println("Something went wrong");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void enterAsAdmin() {
        System.out.println("Enter admin password: ");
        String password = scanner.nextLine();

        try {
            boolean status = AuthorizationService.getInstance().enterAdmin(password);
            if (status) {
                System.out.println("Admin entered");
                new AdminApplication(userController, transactionController).start();
                this.running = false;
            } else {
                System.out.println("Incorrect admin password");
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printOptions() {
        System.out.println("1. Login");
        System.out.println("2. Register");
        System.out.println("3. Enter as admin");
    }

    private void invalidOption() {
        System.out.println("Invalid option. Please select a valid option from the menu.");
    }

    private void mainMenu() {
        System.out.println("Welcome to the Finance Tracker");
        printOptions();
    }
}