package repositories;

import entities.Account;

import java.util.HashMap;
import java.util.List;

public class CurrencyRepository extends FileWorker {
    public static HashMap<String, Double> currencies = new HashMap();
    public static AccountRepository accountRepository = new AccountRepository();

    public static String FILE = "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/currency";

    public HashMap<String, Double> updateCurrency()  {
        Object object = deserializeObject(FILE);
        if(object instanceof HashMap<?,?>) {
            currencies = (HashMap<String, Double>) object;
        }
        return currencies;
    }

    public HashMap<String, Double> allCurrencies() {
        Object object = deserializeObject(FILE);
        HashMap<String, Double> currencies = new HashMap<>();
        if(object instanceof HashMap<?, ?>) {
            currencies = (HashMap<String, Double>) object;
        }
        return currencies;
    }

    public Double convert(Double amount, Long accountIdFrom, Long accountIdTo) {
        Account account1 = accountRepository.findById(accountIdFrom);
        Account account2 = accountRepository.findById(accountIdTo);

        Double result = 0.0;
        HashMap<String, Double> currencyMap = allCurrencies();

            if(currencyMap.containsKey("USD/BYN") && account1.getCurrency().equals("USD") && account2.getCurrency().equals("BYN")) {
                result = amount * currencyMap.get("USD/BYN");
            }
            if(currencyMap.containsKey("USD/EUR") && account1.getCurrency().equals("USD") && account2.getCurrency().equals("EUR")) {
                result = amount * currencyMap.get("USD/EUR");
            }
            if(currencyMap.containsKey("USD/RUB") && account1.getCurrency().equals("USD") && account2.getCurrency().equals("RUB")) {
                result = amount * currencyMap.get("USD/RUB");
            }

            if(currencyMap.containsKey("EUR/BYN") && account1.getCurrency().equals("EUR") && account2.getCurrency().equals("BYN")) {
            result = amount * currencyMap.get("EUR/BYN");
            }
            if(currencyMap.containsKey("EUR/USD") && account1.getCurrency().equals("EUR") && account2.getCurrency().equals("USD")) {
            result = amount * currencyMap.get("EUR/USD");
            }
            if(currencyMap.containsKey("EUR/RUB") && account1.getCurrency().equals("EUR") && account2.getCurrency().equals("RUB")) {
            result = amount * currencyMap.get("EUR/RUB");
            }

            if(currencyMap.containsKey("RUB/BYN") && account1.getCurrency().equals("RUB") && account2.getCurrency().equals("BYN")) {
            result = amount * currencyMap.get("RUB/BYN");
            }
            if(currencyMap.containsKey("RUB/USD") && account1.getCurrency().equals("RUB") && account2.getCurrency().equals("USD")) {
            result = amount * currencyMap.get("RUB/USD");
            }
            if(currencyMap.containsKey("RUB/EUR") && account1.getCurrency().equals("RUB") && account2.getCurrency().equals("EUR")) {
            result = amount * currencyMap.get("RUB/EUR");
            }

            if(currencyMap.containsKey("BYN/RUB") && account1.getCurrency().equals("BYN") && account2.getCurrency().equals("RUB")) {
            result = amount * currencyMap.get("BYN/RUB");
            }
            if(currencyMap.containsKey("BYN/USD") && account1.getCurrency().equals("BYN") && account2.getCurrency().equals("USD")) {
            result = amount * currencyMap.get("BYN/USD");
            }
            if(currencyMap.containsKey("BYN/EUR") && account1.getCurrency().equals("BYN") && account2.getCurrency().equals("EUR")) {
            result = amount * currencyMap.get("BYN/EUR");
            }

        return result;
    }
}
