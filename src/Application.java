import controllers.IUserController;
import controllers.ITransactionController;
import models.Transaction;
import models.User;

import java.util.List;
import java.util.Scanner;

public class Application {
    private final IUserController userController;
    private final ITransactionController transactionController;
    private final Scanner scanner = new Scanner(System.in);

    public Application(IUserController userController, ITransactionController transactionController) {
        this.userController = userController;
        this.transactionController = transactionController;
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
                    case 4:
                        addTransaction();
                        break;
                    case 5:
                        deleteTransaction();
                        break;
                    case 6:
                        getAllTransactions();
                        break;
                    case 7:
                        getUserBalance();
                        break;
                    case 8:
                        getTransactionsFromThisMonth();
                        break;
                }
            } catch (Exception e) {
                System.out.println("Something went wrong");
            }
        }
    }

    private void printOptions() {
        System.out.println("1. Add User");
        System.out.println("2. Get All Users");
        System.out.println("3. Get User By Id");
        System.out.println("4. Add transaction");
        System.out.println("5. Delete transaction");
        System.out.println("6. Get All Transactions");

        System.out.println("7. Get user balance");
        System.out.println("8. Get transactions from this month");
    }

    private void addUser() {
        System.out.println("Please enter your name: ");
        String name = scanner.nextLine();
        System.out.println("Please enter your surname: ");
        String surname = scanner.nextLine();
        System.out.println("Please enter your preferred currency: ");
        String currency = scanner.nextLine();

        System.out.println(userController.addUser(name, surname, currency));
    }

    private void getAllUsers() {
        List<User> users = userController.getAllUsers();

        if (users.isEmpty()) {
            System.out.println("No users found");
        }

        for (User user : userController.getAllUsers()) {
            System.out.println(user.toString() + "/n");
        }
    }

    private void getUserById() {
        System.out.println("Enter id:");
        int id = Integer.parseInt(scanner.nextLine());
        System.out.println(userController.getUserById(id));
    }

    private void addTransaction() {
        System.out.println("Please enter your amount: ");
        int amount = Integer.parseInt(scanner.nextLine());
        System.out.println("Which user this transaction belongs to? ");
        int userId = Integer.parseInt(scanner.nextLine());
        Transaction transaction = transactionController.addTransaction(userId, amount);
        if (transaction != null) {
            System.out.println("Transaction added successfully");
            System.out.println(transaction.toString());
        } else {
            System.out.println("Something went wrong");
        }
    }

    private void deleteTransaction() {
        System.out.println("Please enter your transaction id: ");
        int transactionId = Integer.parseInt(scanner.nextLine());
        boolean status = transactionController.deleteTransaction(transactionId);
        if (status) {
            System.out.println("Transaction deleted successfully");
        } else {
            System.out.println("Something went wrong");
        }
    }

    private void getAllTransactions() {
        List<Transaction> transactions = transactionController.getAllTransactions();
        if (transactions.isEmpty()) {
            System.out.println("No transactions found");
        }

        for (Transaction transaction : transactionController.getAllTransactions()) {
            System.out.println(transaction.toString() + "/n");
        }
    }

    private void getUserBalance() {
        System.out.println("Enter user id");
        int userId = Integer.parseInt(scanner.nextLine());
        String result = userController.getBalance(userId);
        System.out.println(result);
    }

    private void getTransactionsFromThisMonth() {
        for (Transaction transaction : transactionController.getTransactionsFromThisMonth()) {
            System.out.println(transaction.toString() + "/n");
        }
    }

    private void mainMenu() {
        System.out.println("Welcome to the Finance Tracker Application");
        printOptions();
    }
}
