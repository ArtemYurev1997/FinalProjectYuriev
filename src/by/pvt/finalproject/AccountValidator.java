package by.pvt.finalproject;

import entities.Account;
import entities.User;
import exceptions.AccountException;
import repositories.AccountRepository;

import java.util.List;
import java.util.Optional;

public class AccountValidator {
    private final AccountRepository accountRepository;

    public AccountValidator(AccountRepository accountRepository) {
        this.accountRepository = accountRepository;
    }

    public void checkIsAccountOfCurrentUser(Long acUserId, Long userCurrentId) {
            if (!acUserId.equals(userCurrentId)) {
                throw new AccountException("Данный счёт не принадлежит владельцу текущей сессии");
            }
        }

    public void isUniqueAccountNumber(String accountNumber) {
        Optional<Account> account = accountRepository.findByAccountNumber(accountNumber);
        if(account.isPresent()) {
            throw new AccountException("Счёт с номером " + accountNumber + " уже существует");
        }
    }
}
