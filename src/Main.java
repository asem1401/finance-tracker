import controllers.ITransactionController;
import controllers.IUserController;
import controllers.TransactionController;
import controllers.UserController;
import data.PostgresDB;
import data.IDB;
import repository.ITransactionRepository;
import repository.IUserRepository;
import repository.TransactionRepository;
import repository.UserRepository;

public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "rikster", "finance-tracker");
        IUserRepository userRepository = new UserRepository(db);
        IUserController userController = new UserController(userRepository);

        ITransactionRepository transactionRepository = new TransactionRepository(db);
        ITransactionController transactionController = new TransactionController(transactionRepository);

        Application app = new Application(userController, transactionController);
        app.start();
        db.closeConnection();
    }
}