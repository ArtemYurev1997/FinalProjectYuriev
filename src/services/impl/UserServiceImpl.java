package services.impl;

import entities.User;
import enums.Role;
import exceptions.ClientException;
import repositories.AccountRepository;
import repositories.UserRepository;
import services.UserService;
import java.util.List;


public class UserServiceImpl implements UserService {
    private UserRepository userRepository;
    private AccountRepository accountRepository;

    public UserServiceImpl(UserRepository userRepository, AccountRepository accountRepository) {
        this.userRepository = userRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public User checkPassword(String login, String password) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().orElseThrow(() -> new ClientException("Пользователь с логином " + login + " не найден"));
        if ((!user.getPassword().equals(password))) {
            throw new ClientException("Не верно введен пароль");
        }
        return user;
    }

    @Override
    public User register(String login, String password, String name, String surname) {
        List<User> users = userRepository.userList();
        boolean isUserPresent = users.stream().anyMatch(user1 -> user1.getLogin().equals(login));
        if (isUserPresent) {
            throw new ClientException("Введённый логин " + login + " уже существует. Введите другой логин.");
        }
        boolean isAdminPresent = users.stream().anyMatch(user2 -> user2.getRole().equals(Role.ADMIN));
        if (!isAdminPresent) {
            User user = new User(name, surname, login, password);
            user.setId(users.get(users.size() - 1).getId() + 1);
            user.setRole(Role.ADMIN);
            return userRepository.addUser(user);
        }
        User user = new User(name, surname, login, password);
        user.setId(users.get(users.size() - 1).getId() + 1);
        user.setRole(Role.CLIENT);
        return userRepository.addUser(user);
    }

    @Override
    public void changePassword(String login, String oldPassword, String newPassword) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().orElseThrow(() -> new ClientException("Клиент с логином" + login + " не найден"));
        if (!user.getPassword().equals(oldPassword)) {
            throw new ClientException("Не верно введён пароль");
        }
        if(!oldPassword.equals(newPassword)) {
            user.setPassword(newPassword);
            userRepository.save(user);
        }
    }


    @Override
    public void changeLogin(String login, String password, String newLogin) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getLogin().equals(login)).findFirst().orElseThrow(() -> new ClientException("Клиент с логином" + login + " не найден"));
        if (!user.getPassword().equals(password)) {
            throw new ClientException("Не верно введён пароль");
        }
        if(!login.equals(newLogin)) {
            user.setLogin(newLogin);
            userRepository.save(user);
        }
    }
}
