package apps;

import controllers.ITransactionController;
import controllers.IUserController;
import models.Transaction;
import models.User;
import services.AuthorizationService;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class UserApplication {
    private final IUserController userController;
    private final ITransactionController transactionController;
    private final Scanner scanner = new Scanner(System.in);

    public UserApplication(IUserController userController, ITransactionController transactionController) {
        this.userController = userController;
        this.transactionController = transactionController;
    }

    public void start() {
        Map<Integer, Runnable> menuActions = Map.of(
                1, this::addTransaction,
                2, this::getAllMyTransactions,
                3, this::getTransactionsFromThisMonth,
                4, this::getMyBalance,
                5, this::getTransactionsFromCategory
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

    private void addTransaction() {
        System.out.println("Please enter the amount you would like to add:");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Enter category");
        String category = scanner.nextLine();

        try {
            User user = AuthorizationService.getInstance().getCurrentUser();
            Transaction transaction = transactionController.addTransaction(user.getId(), amount, category);

            System.out.println(transaction != null ? "Transaction added successfully" : "Something went wrong");
            System.out.println(transaction != null ? transaction.toString() : "");
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getAllMyTransactions() {
        try {
            User user = AuthorizationService.getInstance().getCurrentUser();
            List<Transaction> transactions = transactionController.getTransactionsByUserID(user.getId());

            if (transactions.isEmpty()) {
                System.out.println("No transactions found");
            } else {
                transactions.forEach(transaction -> System.out.println(transaction.toString()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTransactionsFromThisMonth() {
        try {
            User user = AuthorizationService.getInstance().getCurrentUser();
            List<Transaction> transactions = transactionController.getTransactionsFromThisMonthByUserID(user.getId());

            if (transactions.isEmpty()) {
                System.out.println("No transactions found");
            } else {
                transactions.forEach(transaction -> System.out.println(transaction.toString()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getMyBalance() {
        User user = AuthorizationService.getInstance().getCurrentUser();

        try {
            System.out.println(userController.getBalance(user.getId()));
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void getTransactionsFromCategory() {
        System.out.println("Please enter the category:");
        String category = scanner.nextLine();

        try {
            User user = AuthorizationService.getInstance().getCurrentUser();

            List<Transaction> transactions = transactionController.getTransactionsByCategoryAndUserID(category, user.getId());
            if (transactions.isEmpty()) {
                System.out.println("No transactions found");
            } else {
                transactions.forEach(transaction -> System.out.println(transaction.toString()));
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
    }

    private void printOptions() {
        System.out.println("1. Add transaction");
        System.out.println("2. Get all my transactions");
        System.out.println("3. Get my transactions from this month");
        System.out.println("4. Get my balance");
        System.out.println("5. Get transactions from category");
    }

    private void invalidOption() {
        System.out.println("Invalid option. Please select a valid option from the menu.");
    }

    private void mainMenu() {
        printOptions();
    }
}
