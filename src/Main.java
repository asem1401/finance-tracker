import controllers.ITransactionController;
import controllers.IUserController;
import controllers.TransactionController;
import controllers.UserController;
import data.PostgresDB;
import data.IDB;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "12345678", "finance-tracker");
        IUserController userController = new UserController(db);
        ITransactionController transactionController = new TransactionController(db);

        Application app = new Application(userController, transactionController);
        app.start();
        db.closeConnection();
    }
}