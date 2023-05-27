package by.pvt.finalproject;

import entities.Account;
import entities.Operation;
import entities.User;
import enums.CurrencyReader;
import enums.Role;
import repositories.CurrencyRepository;
import repositories.FileWorker;

import java.util.*;

public class Start {
    public static void main(String[] args)  {

    /*User user1 = new User("Anton", "Tim", "Rex", "1234", Role.CLIENT);
        User user2 = new User("Dmitriy" , "Lixx", "Sql", "12345", Role.CLIENT);
        User user3 = new User("Oleg" , "Olegov", "Pork", "1221", Role.CLIENT);
        User user4 = new User("Tim" , "Sorokin", "Yepv", "1366", Role.ADMIN);
        List<User> users = new ArrayList<>();
        users.add(user1);
        users.add(user2);
        users.add(user3);
        users.add(user4);
        for(User user: users) {
            user.setId();
        }
        FileWorker.serializeObject(users, "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/users");
        FileWorker.deserializeObject("C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/users");


        Account account1 = new Account(1L,"23409", "BYN", 2500.0);
        Account account2 = new Account(1L,"75442", "EUR", 4400.0);
        Account account3 = new Account(1L, "52421", "RUB", 12000.0);
        Account account4 = new Account( 1L,"40989", "USD", 840.0);
        Account account5 = new Account(2L, "62313", "BYN", 6200.0);
        Account account6 = new Account(2L,"75442", "EUR", 3000.0);
        Account account7 = new Account(2L, "52421", "RUB", 12000.0);
        Account account8 = new Account( 2L,"69069", "USD", 1500.0);
        Account account9 = new Account(3L,"96331", "RUB", 24000.0);
        Account account10 = new Account(3L,"80547", "EUR", 13000.0);
        Account account11 = new Account(3L, "62313", "USD", 3000.0);
        Account account12 = new Account(3L, "62313", "BYN", 5500.0);
        List<Account> listAccounts = new ArrayList<>();
        listAccounts.add(account1);
        listAccounts.add(account2);
        listAccounts.add(account3);
        listAccounts.add(account4);
        listAccounts.add(account5);
        listAccounts.add(account6);
        listAccounts.add(account7);
        listAccounts.add(account8);
        listAccounts.add(account9);
        listAccounts.add(account10);
        listAccounts.add(account11);
        listAccounts.add(account12);
        for(Account account: listAccounts) {
                account.setId();
        }
        FileWorker.serializeObject(listAccounts, "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/accountsFile");
        FileWorker.deserializeObject("C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/accountsFile");


        Operation operation1 = new Operation(1L, 1L, "Anton", "Tim","1234567",  "Oleg", "Olegov", "1234321", 2L, 20.0, "BYN", new Date(1685013489000L), 0.1, true);
        Operation operation2 = new Operation(2L, 2L, "Leon", "Kirilin","1237584",  "Oleg", "Olegov", "1234321", 2L, 50.0, "BYN", new Date(1685049669000L), 0.1, true);
        List<Operation> operations = new ArrayList<>();
        operations.add(operation1);
        operations.add(operation2);
        FileWorker.serializeObject(operations, "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/operations");
        FileWorker.deserializeObject("C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/operations");*/

        CurrencyReader currencyReader = new CurrencyReader(new CurrencyRepository());
        Timer timer = new Timer();
        timer.schedule(currencyReader, 0, 10800000);
        CurrencyChanger currencyChanger = new CurrencyChanger();
        currencyChanger.process();
    }
}
