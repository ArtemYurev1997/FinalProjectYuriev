package controller;

import entities.User;
import services.UserService;

import java.util.Scanner;

public class UserController {
    private  UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }


    public User authenticate(Scanner scanner) {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        return userService.checkPassword(login, password);
    }

    public User register(Scanner scanner) {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        System.out.println("Введите имя");
        String name = scanner.nextLine();
        System.out.println("Введите фамилию");
        String surname = scanner.nextLine();
        return userService.register(login, password, name, surname);
    }
}
