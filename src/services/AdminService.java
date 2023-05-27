package services;

import entities.Account;
import entities.User;

import java.util.List;
import java.util.Scanner;

public interface AdminService {
    List<User> listAllClients();

    void deleteClient(Long clientId);

    void changePassword(Scanner scanner);

    void changeLogin(Scanner scanner);

    Account createAccount(Scanner scanner, Long id);

    void findByLogin(String login);
}
