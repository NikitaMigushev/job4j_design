package ru.job4j.ood.srp;

public interface UserController {
    void registerUser(User user);
    void authenticateUser(String username, String password);
    void updateUserProfile(User user);
    void deleteUser(String username);
}
