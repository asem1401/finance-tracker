package controllers;

import data.IDB;
import models.User;
import repository.IUserRepository;

import java.util.List;

public class UserController implements IUserController {
    private final IUserRepository userRepository;

    public UserController(IUserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User addUser(String name, String surname, String currency) {
        return userRepository.addUser(name, surname, currency);
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
