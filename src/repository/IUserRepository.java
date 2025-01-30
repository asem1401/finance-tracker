package repository;

import models.User;

import java.util.List;

public interface IUserRepository {
    User addUser(String name, String surname, String currency);
    User getUserById(int id);
    List<User> getAllUsers();
    String getBalance(int id);
}
