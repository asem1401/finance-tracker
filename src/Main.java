import controllers.IUserController;
import controllers.UserController;
import data.PostgresDB;
import data.IDB;


public class Main {
    public static void main(String[] args) {
        IDB db = new PostgresDB("jdbc:postgresql://localhost:5432", "postgres", "rikster", "finance-tracker");
        IUserController userController = new UserController(db);

        Application app = new Application(userController);
        app.start();
        db.closeConnection();
    }
}