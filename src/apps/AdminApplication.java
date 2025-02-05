package apps;

import controllers.ITransactionController;
import controllers.IUserController;
import models.Transaction;
import models.User;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class AdminApplication {
    private final IUserController userController;
    private final ITransactionController transactionController;
    private final Scanner scanner = new Scanner(System.in);

    public AdminApplication(IUserController userController, ITransactionController transactionController) {
        this.userController = userController;
        this.transactionController = transactionController;
    }

    public void start() {
        Map<Integer, Runnable> menuActions = Map.of(
                1, this::addUser,
                2, this::getAllUsers,
                3, this::getUserById,
                4, this::deleteUserById,
                5, this::addTransaction,
                6, this::deleteTransaction,
                7, this::getAllTransactions,
                8, this::getUserBalance,
                9, this::getTransactionsFromThisMonth
        );

        while (true) {
            mainMenu();
            try {
                int option = Integer.parseInt(scanner.nextLine());
                menuActions.getOrDefault(option, this::invalidOption).run();
            } catch (Exception e) {
                invalidOption();
            }
        }
    }

    private void addUser() {
        System.out.println("Please enter username");
        String username = scanner.nextLine();
        System.out.println("Please enter password");
        String password = scanner.nextLine();
        System.out.println("Please enter your preferred currency: ");
        String currency = scanner.nextLine();

        try {
            User user = userController.addUser(username, password, currency);
            System.out.println(user != null ? "User added successfully \n" + user.toString() : "Something went wrong");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllUsers() {
        List<User> users = userController.getAllUsers();
        if (users.isEmpty()) {
            System.out.println("No users found");
        } else {
            users.forEach(user -> System.out.println(user.toString()));
        }
    }

    private void getUserById() {
        System.out.println("Enter id:");
        int id = Integer.parseInt(scanner.nextLine());
        try {
            User user = userController.getUserById(id);
            System.out.println(user != null ? user : "Something went wrong");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteUserById() {
        System.out.println("Enter id:");
        int id = Integer.parseInt(scanner.nextLine());
    }

    private void addTransaction() {
        System.out.println("Please enter your amount: ");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Which user this transaction belongs to? ");
        int userId = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter category:");
        String category = scanner.nextLine();

        try {
            Transaction transaction = transactionController.addTransaction(userId, amount, category);
            System.out.println(transaction != null ? "Transaction added successfully" : "Something went wrong");
            System.out.println(transaction != null ? transaction.toString() : "");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void deleteTransaction() {
        System.out.println("Please enter your transaction id: ");
        int transactionId = Integer.parseInt(scanner.nextLine());

        try {
            boolean status = transactionController.deleteTransaction(transactionId);
            System.out.println(status ? "Transaction deleted successfully" : "Something went wrong");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllTransactions() {
        List<Transaction> transactions = transactionController.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
        } else {
            transactions.forEach(transaction -> System.out.println(transaction.toString()));
        }
    }

    private void getUserBalance() {
        System.out.println("Enter user id");
        int userId = Integer.parseInt(scanner.nextLine());

        try {
            System.out.println(userController.getBalance(userId));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTransactionsFromThisMonth() {
        List<Transaction> transactions = transactionController.getTransactionsFromThisMonth();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
        } else {
            transactions.forEach(transaction -> System.out.println(transaction.toString()));
        }
    }

    private void printOptions() {
        System.out.println("1. Add User");
        System.out.println("2. Get All Users");
        System.out.println("3. Get User By Id");
        System.out.println("4. Delete User By Id");
        System.out.println("5. Add transaction");
        System.out.println("6. Delete transaction by id");
        System.out.println("7. Get All Transactions");
        System.out.println("8. Get user balance");
        System.out.println("9. Get transactions from this month");
    }

    private void invalidOption() {
        System.out.println("Invalid option. Please select a valid option from the menu.");
    }

    private void mainMenu() {
        printOptions();
    }
}
