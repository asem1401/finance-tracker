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
    public User addUser(String name, String surname, String currency) throws IllegalArgumentException{
        if (!validCurrencies.contains(currency)) {
            throw new IllegalArgumentException(currency + " is not a valid currency.");
        }

        if (name == null || name.trim().isEmpty()) {
            throw new IllegalArgumentException("Name cannot be empty.");
        }
        if (surname == null || surname.trim().isEmpty()) {
            throw new IllegalArgumentException("Surname cannot be empty.");
        }

        return userRepository.addUser(name, surname, currency);
    }

    @Override
    public User getUserById(int id) throws IllegalArgumentException {
        if (id <= 0) {
            throw new IllegalArgumentException("Invalid user ID. Must be a positive number.");
        }

        return userRepository.getUserById(id);
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
