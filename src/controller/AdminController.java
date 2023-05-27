package controller;

import entities.Account;
import entities.Operation;
import entities.User;
import exceptions.ExitException;
import exceptions.FileException;
import services.AccountService;
import services.AdminService;
import services.OperationService;
import services.UserService;

import java.util.List;
import java.util.Scanner;

public class AdminController  {
    private final AccountService accountService;
    private final OperationService operationService;
    private final AdminService adminService;
    private final UserService userService;


    public AdminController(AccountService accountService, OperationService operationService, AdminService adminService, UserService userService) {
        this.accountService = accountService;
        this.operationService = operationService;
        this.adminService = adminService;
        this.userService = userService;
    }

    public void adminProcess(Scanner scanner, User user)  {
        System.out.println("Выберите действие: \n" +
                "1 - Просмотр всех клиентов \n" +
                "2 - Просмотр операций по клиенту \n" +
                "3 - Удаление клиента \n" +
                "4 - Смена пароля \n" +
                "5 - Смена логина \n" +
                "6 - Создание счёта \n" +
                "7 - Просмотр счёта с комиссиями \n" +
                "8 - Просмотр операций с неоплаченными комиссиями \n" +
                "9 - Перевод комиссии на счёт \n" +
                "10 - Получить сумму комиссий не переведённых \n" +
                "11 - Регистрация пользователя \n" +
                "12 - Просмотр клиента по логину \n" +
                "13 - Просмотр всех счетов \n" +
                "14 - Просмотр всех операций \n" +
                "15 - Выход \n");

        String chooseVariant = scanner.nextLine();
        long clientId;
        switch (chooseVariant) {
            case "1":
                List<User> users = adminService.listAllClients();
                System.out.println(users);
                break;
            case "2":
                System.out.println("Введите id клиента");
                clientId = scanner.nextLong();
                List<Operation> operations = operationService.getOperationByClient(clientId);
                System.out.println(operations);
                break;
            case "3":
                System.out.println("Введите id клиента");
                clientId = scanner.nextLong();
                adminService.deleteClient(clientId);
                break;
            case "4":
                changePassword(scanner);        //??
                break;
            case "5":
                changeLogin(scanner);
                break;
            case "6":
                System.out.println(createAccount(scanner, user.getId()));//??
                break;
            case "7":
                System.out.println(getByAccountNumber(scanner));//??
                break;
            case "8":
                System.out.println(operationService.operationsByCommission());
                break;
            case "9":
                System.out.println("Введите id счёта");
                Long acId = scanner.nextLong();
                operationService.passAllCommissions(acId, user.getId());
                break;
            case "10":
                System.out.println(operationService.calculateCommission());
                break;
            case "11":
                System.out.println(register(scanner));
                break;
            case "12":
                System.out.println("Введите логин");
                String login = scanner.nextLine();
                adminService.findByLogin(login);
                break;
            case "13":
                System.out.println(accountService.allAccounts());
                break;
            case "14":
                System.out.println(operationService.readAllOperations());
                break;
            case "15":
                throw new ExitException();
            default:
                throw new FileException("Не верно введена операция");
        }
        adminProcess(scanner, user);
    }

    private Account getByAccountNumber(Scanner scanner) {
        System.out.println("Введите номер счёта");
        String accountNumber = scanner.nextLine();
        return accountService.getByNumberAccount(accountNumber);
    }

    private Account createAccount(Scanner scanner, Long id) {
        System.out.println("Введите номер счёта");
        String accountNumber = scanner.nextLine();
        System.out.println("Введите валюту счёта");
        String currency = scanner.nextLine();
        return accountService.createAccount(id, accountNumber, currency);
    }

    private void changeLogin(Scanner scanner) {
        adminService.changeLogin(scanner);
    }

    private void changePassword(Scanner scanner) {
        adminService.changePassword(scanner);
    }

    private User register(Scanner scanner) {
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
