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
    public String addUser(String name, String surname, String currency) {
        if (!validCurrencies.contains(currency)) {
            return currency + " is not a valid currency.";
        }

        User user = userRepository.addUser(name, surname, currency);

        if (user != null) {
            return "User added successfully \n" + user.toString();
        } else {
            return "Something went wrong";
        }
    }

    @Override
    public User getUserById(int id) {
        return userRepository.getUserById(id);
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.getAllUsers();
    }

    public String getBalance(int id) {
        return userRepository.getBalance(id);
    }
}
