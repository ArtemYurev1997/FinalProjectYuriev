package services.impl;

import entities.Account;
import entities.User;
import enums.Role;
import exceptions.AccountException;
import exceptions.BankException;
import exceptions.ClientException;
import repositories.AccountRepository;
import repositories.CurrencyRepository;
import repositories.OperationRepository;
import repositories.UserRepository;
import services.AdminService;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.Scanner;

public class AdminServiceImpl implements AdminService {
    private AccountRepository accountRepository;
    private OperationRepository operationRepository;
    private UserRepository userRepository;
    private CurrencyRepository currencyRepository;

    public AdminServiceImpl(AccountRepository accountRepository, OperationRepository operationRepository, UserRepository userRepository, CurrencyRepository currencyRepository) {
        this.accountRepository = accountRepository;
        this.operationRepository = operationRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
    }

    @Override
    public List<User> listAllClients() {
        return userRepository.userList();
    }

    @Override
    public void deleteClient(Long clientId) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getId().equals(clientId)).findFirst().orElseThrow(() -> new ClientException("Такого клиента с id" + clientId + " нет"));
        users.remove(user);
        for (User user1 : users) {
            user1.setId(user1.getId() - 1);
        }
        System.out.println(users);

    }

    @Override
    public void changePassword(Scanner scanner) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getLogin().equals(scanner.nextLine())).findFirst().orElseThrow(() -> new ClientException("Клиент с логином" + scanner.nextLine() + " не найден"));
        if (!user.getPassword().equals(scanner.nextLine())) {
            throw new ClientException("Не верно введён пароль");
        }
        if (!user.getPassword().equals(scanner.nextLine())) {
            user.setPassword(scanner.nextLine());
            userRepository.save(user);
        }
    }

    @Override
    public void changeLogin(Scanner scanner) {
        List<User> users = userRepository.userList();
        User user = users.stream().filter(user1 -> user1.getLogin().equals(scanner.nextLine())).findFirst().orElseThrow(() -> new ClientException("Клиент с логином" + scanner.nextLine() + " не найден"));
        if (!user.getPassword().equals(scanner.nextLine())) {
            throw new ClientException("Не верно введён пароль");
        }
        user.setLogin(scanner.nextLine());
        userRepository.save(user);
    }

    @Override
    public Account createAccount(Scanner scanner, Long id) {
        User user = userRepository.findById(id);
        Account account = new Account();
        HashMap<String, Double> currency = currencyRepository.allCurrencies();
        if (!currency.equals("BYN") && !currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
            throw new BankException("Такой валюты не существует");
        }
        isUniqueAccountNumberForAdmin(scanner.nextLine());
        if (user.getRole().equals(Role.ADMIN)) {
            account.setAccountNumber(scanner.nextLine());
            account.setCurrency(scanner.nextLine());
            account.setId();
            account.setUserId(scanner.nextLong());
            account.setValue(0.0);
            accountRepository.addAccount(account);
            accountRepository.saveChangesWithAccount();
        } else if (user.getRole().equals(Role.CLIENT)) {
            account.setAccountNumber(scanner.nextLine());
            account.setCurrency(scanner.nextLine());
            account.setId();
            account.setUserId(scanner.nextLong());
            account.setValue(0.0);
            accountRepository.addAccount(account);
            accountRepository.saveChangesWithAccount();
        }
        return account;
    }

    @Override
    public void findByLogin(String login) {
        User user = userRepository.findByLogin(login);
        System.out.println(user);
    }

    public void isUniqueAccountNumberForAdmin(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if (account.isPresent()) {
            throw new AccountException("Счёт с номером " + accountNumber + " уже существует");
        }
    }
}
