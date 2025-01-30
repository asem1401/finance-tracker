package controllers;

import models.User;

import java.util.List;

public interface IUserController {
    String addUser(String name, String surname, String currency);
    User getUserById(int id);
    List<User> getAllUsers();
    String getBalance(int id);
}
