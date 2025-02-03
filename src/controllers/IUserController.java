package controllers;

import models.User;

import java.util.List;

public interface IUserController {
    User addUser(String username, String password, String currency);
    User getUserById(int id);
    List<User> getAllUsers();
    String getBalance(int id);
    User getUserByUsername(String username);
}
