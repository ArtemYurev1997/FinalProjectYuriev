package services;

import entities.User;

public interface UserService {

    User checkPassword(String login, String password);

    User register(String login, String password, String name, String surname);

    void changePassword(String login, String oldPassword, String newPassword);

    void changeLogin(String login, String password, String newLogin);
}
