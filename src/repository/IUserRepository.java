package repository;

import models.User;

import java.util.List;

public interface IUserRepository {
    User addUser(String username, String password, String currency);
    User getUserById(int id);
    List<User> getAllUsers();
    String getBalance(int id);
    User getUserByUsername(String username);
}
