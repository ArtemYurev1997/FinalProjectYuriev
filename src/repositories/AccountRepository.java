package repositories;

import entities.Account;
import exceptions.AccountException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


public class AccountRepository extends FileWorker {
    public static List<Account> accounts = new ArrayList<>();

    public static String FILE = "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/accountsFile";

    public List<Account> updateAccounts()  {
        Object object = deserializeObject(FILE);
        if(object instanceof List<?>) {
            accounts = (List<Account>) object;
        }
        return accounts;
    }

    public List<Account> allAccounts() {
        Object object = deserializeObject(FILE);
        List<Account> accounts = new ArrayList<>();
        if(object instanceof List<?>) {
            accounts = (List<Account>) object;
        }
        return accounts;
    }

    public List<Account> findAccountsByClient(Long clientId) {//?
        List<Account> accountsByClient = accounts.stream().filter(account -> account.getUserId().equals(clientId)).collect(Collectors.toList());
        if (accountsByClient.isEmpty()) {
            return new ArrayList<>();
        }
        return accountsByClient;
    }

    public Account findById(Long accountId) {
        return accounts.stream().filter(account -> account.getId().equals(accountId)).findFirst().orElseThrow(() -> new AccountException("Счёт с id " + accountId + " не найден"));
}

    public Optional<Account> findByAccountNumber(String accountNumber) {
        return accounts.stream().filter(account -> account.getAccountNumber().equals(accountNumber)).findFirst();
}

    public void saveChangesWithAccount() {
        serializeObject(accounts, FILE);
        updateAccounts();
    }

    public Account addAccount(Account account) {
        accounts = allAccounts();
        accounts.add(account);
        serializeObject(accounts, FILE);
        return account;
    }

    public Long getLastId() {
        accounts = allAccounts();
        if(accounts.isEmpty()) {
            return 0L;
        }
        return accounts.get(accounts.size() - 1).getId();
    }
}
