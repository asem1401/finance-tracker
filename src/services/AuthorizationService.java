package services;

import controllers.IUserController;
import models.User;

public class AuthorizationService {
    private static volatile AuthorizationService instance;
    private final static String AdminPassword = "admin";
    private User currentUser;
    private boolean isAdmin;
    private IUserController userController;

    private AuthorizationService() {}

    public static AuthorizationService getInstance() {
        if (instance == null) {
            synchronized (AuthorizationService.class) {
                if (instance == null) {
                    instance = new AuthorizationService();
                }
            }
        }
        return instance;
    }

    public void init(IUserController userController) {
        this.userController = userController;
    }

    public User getCurrentUser() {
        return currentUser;
    }

    public boolean registerUser(String username, String password, String currency) {
        this.currentUser = userController.addUser(username, password, currency);
        return this.currentUser != null;
    }

    public boolean loginUser(String username, String password) {
        User user = userController.getUserByUsername(username);

        if (user == null) {
            return false;
        }

        if (!user.getPassword().equals(password)) {
            return false;
        }

        this.currentUser = user;
        return true;
    }

    public boolean enterAdmin(String adminPassword) throws IllegalArgumentException{
        if (adminPassword == null || adminPassword.isEmpty()) {
            throw new IllegalArgumentException("Password cannot be null or empty");
        }

        if (!AdminPassword.equals(adminPassword)) {
            return false;
        }

        this.isAdmin = true;
        return true;
    }
}