package controllers;

import models.User;
import repository.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository userRepository;
    private final List<String> validCurrencies = List.of("USD", "KZT", "RUB", "EUR");

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(String username, String password, String currency) throws IllegalArgumentException{
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Username cannot be empty.");
        }
        if (password == null || password.trim().isEmpty()) {
            throw new IllegalArgumentException("Password cannot be empty.");
        }
        if (currency == null || currency.trim().isEmpty()) {
            throw new IllegalArgumentException("Currency cannot be empty.");
        }
        if (!validCurrencies.contains(currency)) {
            throw new IllegalArgumentException(currency + " is not a valid currency.");
        }

        return userRepository.addUser(username, password, currency);
    }

    @Override
    public User getUserById(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }

        return userRepository.getUserById(id);
    }

    public User getUserByUsername(String username) throws IllegalArgumentException {
        if (username == null || username.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }

        return userRepository.getUserByUsername(username);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String getBalance(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }
        return userRepository.getBalance(id);
    }
}
