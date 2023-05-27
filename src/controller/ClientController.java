package controller;

import entities.Operation;
import entities.User;
import exceptions.ExitException;
import exceptions.FileException;
import repositories.CurrencyRepository;
import services.AccountService;
import services.OperationService;
import services.UserService;

import java.util.Scanner;

public class ClientController {

    private final AccountService accountService;
    private final OperationService operationService;
    private final UserService userService;
    private final CurrencyRepository currencyRepository;

    public ClientController(AccountService accountService, OperationService operationService, UserService userService, CurrencyRepository currencyRepository) {
        this.accountService = accountService;
        this.operationService = operationService;
        this.userService = userService;
        this.currencyRepository = currencyRepository;
    }

    public void clientProcess(Scanner scanner, User user) {
        System.out.println("Добро пожаловать " + user.getName() + " " + user.getSurname()  + " Выберите действие: \n" +
                "1 - Обмен валют \n" +
                "2 - Перевод средств на другой счёт \n" +
                "3 - Просмотр баланса на счетах \n" +
                "4 - Просмотр текущих курсов \n" +
                "5 - Создание счёта \n" +
                "6 - Внесение денег на счёт \n" +
                "7 - Удаление счёта \n" +
                "8 - Смена пароля \n" +
                "9 - Смена логина \n" +
                "10 - Посчитать общую сумму на счетах в BYN \n" +
                "11 - Вывести все операции по счёту за промежуток времени \n" +
                "12 - Выход \n");
        String chooseVariant = scanner.nextLine();
        switch (chooseVariant) {
            case "1":
            case "2":
                System.out.println(changeCurrency(scanner, user.getId()));
                break;
            case "3":
                System.out.println(accountService.getAccountByClient(user.getId()));
                break;
            case "4":
                System.out.println(currencyRepository.allCurrencies());
                break;
            case "5":
                System.out.println("Введите номер счёта");
                String accountNumber = scanner.nextLine();
                System.out.println("Введите валюту счёта");
                String currency = scanner.nextLine();
                System.out.println(accountService.createAccount(user.getId(), accountNumber, currency));
                break;
            case "6":
                System.out.println("Введите id счёта");
                Long accountId = scanner.nextLong();
                System.out.println("Введите сумму внесения");
                Double sum = scanner.nextDouble();
                String sc = scanner.nextLine();
                System.out.println(accountService.passMoney(accountId, sum));
                break;
            case "7":
                System.out.println("Введите id счёта");
                Long id = scanner.nextLong();
                accountService.deleteAccount(id, user.getId());
                break;
            case "8":
                changePassword(scanner);
                break;
            case "9":
                changeLogin(scanner);
                break;
            case "10":
                System.out.println(accountService.calculateOfSumAccounts(user.getId()));
                break;
            case "11":
                System.out.println("Дата с которой будет осуществляться поиск. Формат даты: yyyy-MM-dd");
                String dateFrom = scanner.nextLine();
                System.out.println("Дата до которой будет осуществляться поиск. Формат даты: yyyy-MM-dd");
                String dateTo = scanner.nextLine();
                System.out.println("Введите id счёта");
                Long acId = scanner.nextLong();
                System.out.println(operationService.operationsByUserAndAccount(user.getId(), acId, dateFrom, dateTo));
                break;
            case "12":
                throw new ExitException();
            default:
                throw new FileException("Не верно введена операция для клиента");
        }
        clientProcess(scanner, user);
    }

    private Operation changeCurrency(Scanner scanner, Long userId) {
        System.out.println("Введите id с которого будут переводить деньги");
        String accountFromId = scanner.nextLine();
        System.out.println("Введите id на котороый будут переводить деньги");
        String accountToId = scanner.nextLine();
        System.out.println("Введите сумму перевода со счёта");
        Double value = scanner.nextDouble();
        String any = scanner.nextLine();
        return operationService.changeCurrency(Long.valueOf(accountFromId), Long.valueOf(accountToId), value, userId);
    }

    private void changePassword(Scanner scanner) {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        System.out.println("Введите новый пароль");
        String newPassword = scanner.nextLine();
        userService.changePassword(login, password, newPassword);
    }

    private void changeLogin(Scanner scanner) {
        System.out.println("Введите логин");
        String login = scanner.nextLine();
        System.out.println("Введите пароль");
        String password = scanner.nextLine();
        System.out.println("Введите новый логин");
        String newLogin = scanner.nextLine();
        userService.changeLogin(login, password, newLogin);
    }
}
