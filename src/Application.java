import controllers.IUserController;
import models.User;

import java.util.Scanner;

public class Application {
    private final IUserController userController;
    private final Scanner scanner = new Scanner(System.in);

    public Application(IUserController userController) {
        this.userController = userController;
    }

    public void start() {
        while (true) {
            mainMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                switch (option) {
                    case 1:
                        addUser();
                        break;
                    case 2:
                        getAllUsers();
                        break;
                    case 3:
                        getUserById();
                        break;
                }
                printOptions();
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
    }

    private void printOptions() {
        System.out.println("1. Add User");
        System.out.println("2. Get All Users");
        System.out.println("3. Get User By Id");
    }

    private void addUser() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Please enter your preferred currency: ");
        String currency = scanner.nextLine();

        User user = userController.addUser(name, surname, currency);
        if (user != null) {
            System.out.println("User added successfully");
            System.out.println(user.toString());
        } else {
            System.out.println(user);
        }

    }

    private void getAllUsers() {
        for (User user : userController.getAllUsers()) {
            System.out.println(user.toString() + "/n");
        }
    }

    private void getUserById() {
        System.out.println("Enter id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(userController.getUserById(id));
    }

    private void mainMenu() {
        System.out.println("Welcome to the Finance Tracker Application");
        printOptions();
    }
}
