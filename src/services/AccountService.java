package services;

import entities.Account;

import java.util.List;
import java.util.Optional;

public interface AccountService {
    Account createAccount(Long clientId, String accountNumber, String currency);

    Account passMoney(Long accountId, Double money);

    List<Account> getAccountByClient(Long clientId);

    List<Account> getAccountByClient(String clientLogin);

    Account getAccountById(Long accountId);

    Account getByNumberAccount(String accountNumber);

    void deleteAccount(Long accountId, Long userId);

    void deleteAccount(String accountNumber, Long userId);

    Double calculateOfSumAccounts(Long userId);

    List<Account> allAccounts();
}
