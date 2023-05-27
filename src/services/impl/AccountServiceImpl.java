package services.impl;

import by.pvt.finalproject.AccountValidator;
import entities.Account;
import entities.User;
import exceptions.AccountException;
import exceptions.BankException;
import repositories.AccountRepository;
import repositories.CurrencyRepository;
import repositories.UserRepository;
import services.AccountService;
import java.util.HashMap;
import java.util.List;



public class AccountServiceImpl implements AccountService {
    private AccountRepository accountRepository;
    private UserRepository userRepository;
    private CurrencyRepository currencyRepository;
    private AccountValidator accountValidator;

    public AccountServiceImpl(AccountRepository accountRepository, UserRepository userRepository, CurrencyRepository currencyRepository, AccountValidator accountValidator) {
        this.accountRepository = accountRepository;
        this.userRepository = userRepository;
        this.currencyRepository = currencyRepository;
        this.accountValidator = accountValidator;
        accountRepository.updateAccounts();
    }

    @Override
    public Account createAccount(Long clientId, String accountNumber, String currency) {
            Account account = new Account();
            if (!currency.equals("BYN") && !currency.equals("RUB") && !currency.equals("USD") && !currency.equals("EUR")) {
                throw new BankException("Такой валюты не существует");
            }
            accountValidator.isUniqueAccountNumber(accountNumber);
            account.setCurrency(currency);
            account.setId(accountRepository.getLastId() + 1);
            account.setAccountNumber(accountNumber);
            account.setValue(0.0);
            account.setUserId(clientId);
            accountRepository.addAccount(account);
            accountRepository.saveChangesWithAccount();
        return account;
    }

    @Override
    public Account passMoney(Long accountId, Double money) {
        Account account = accountRepository.findById(accountId);
        account.setValue(account.getValue() + money);
        accountRepository.saveChangesWithAccount();
        return account;
    }

    @Override
    public List<Account> getAccountByClient(Long clientId) {
        return accountRepository.findAccountsByClient(clientId);
    }

    @Override
    public List<Account> getAccountByClient(String clientLogin) {
        User user = userRepository.findByLogin(clientLogin);
        return accountRepository.findAccountsByClient(user.getId());
    }

    @Override
    public Account getAccountById(Long accountId) {
        return accountRepository.findById(accountId);
    }

    @Override
    public Account getByNumberAccount(String accountNumber) {
        List<Account> accounts = accountRepository.allAccounts();
        Account account = accounts.stream().filter(account1 -> account1.getAccountNumber().equals(accountNumber)).findFirst().orElseThrow(() -> new AccountException("Не найден счёт под номером " + accountNumber));
        return account;
    }

    @Override
    public void deleteAccount(Long accountId, Long userId) {
        List<Account> accounts = accountRepository.findAccountsByClient(userId);
        Account account  = accountRepository.findById(accountId);
         accounts.remove(account);
        for(Account account1: accounts) {
            account1.setId(account1.getId() - 1);
        }
        System.out.println(accounts);

    }

    @Override
    public void deleteAccount(String accountNumber, Long userId) {
        List<Account> accounts = accountRepository.allAccounts();
        Account account = accounts.stream().filter(account1 -> account1.getAccountNumber().equals(accountNumber)).findFirst().get();
        accounts.remove(account);
        for(Account account1: accounts) {
            account1.setId(account1.getId() - 1);
        }
        System.out.println(accounts);
    }

    @Override
    public Double calculateOfSumAccounts(Long userId) {
        List<Account> accounts = accountRepository.findAccountsByClient(userId);
        HashMap<String, Double> currency = currencyRepository.allCurrencies();
        double sum = 0.0;
        for(Account account: accounts) {
            if (account.getCurrency().equals("USD")) {
               if(currency.containsKey("USD/BYN")) {
                    sum += account.getValue() * currency.get("USD/BYN");
                }
            }
            if (account.getCurrency().equals("EUR")) {
                if (currency.containsKey("EUR/BYN")) {
                    sum += account.getValue() * currency.get("EUR/BYN");
                }
            }
            if (account.getCurrency().equals("RUB")) {
                if (currency.containsKey("RUB/BYN")) {
                    sum += account.getValue() * currency.get("RUB/BYN");
                }
            }
            if (account.getCurrency().equals("BYN")) {
                    sum += account.getValue();
                }
        }
        return sum;
    }

    @Override
    public List<Account> allAccounts() {
        return accountRepository.allAccounts();
    }
}
