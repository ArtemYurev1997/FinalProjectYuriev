package enums;

import repositories.CurrencyRepository;
import repositories.FileWorker;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.TimerTask;

public class CurrencyReader extends TimerTask implements Serializable  {

    private HashMap<String, Double> exchangeCurrency = new HashMap<>();
    private CurrencyRepository currencyRepository = new CurrencyRepository();
    public CurrencyReader() {
    }

    public CurrencyReader(CurrencyRepository currencyRepository) {
        this.currencyRepository = currencyRepository;
    }


    public HashMap<String, Double> getExchangeCurrency() {
        return exchangeCurrency;
    }

    public void setExchangeCurrency(String key, Double value) {
        this.exchangeCurrency.put(key, value);
    }

    @Override
    public void run() {
        exchangeCurrency.put("USD/EUR", 0.92);
        exchangeCurrency.put("USD/RUB", 80.00);
        exchangeCurrency.put("USD/BYN", 2.88);
        exchangeCurrency.put("EUR/USD", 1.08);
        exchangeCurrency.put("EUR/RUB", 86.70);
        exchangeCurrency.put("EUR/BYN", 3.14);
        exchangeCurrency.put("RUB/USD", 0.013);
        exchangeCurrency.put("RUB/EUR", 0.012);
        exchangeCurrency.put("RUB/BYN", 0.037);
        exchangeCurrency.put("BYN/USD", 0.34);
        exchangeCurrency.put("BYN/EUR", 0.32);
        exchangeCurrency.put("BYN/RUB", 27.30);
       /* FileWorker.serializeObject(getExchangeCurrency(), "C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/currency");
        FileWorker.deserializeObject("C:/Users/artem/IdeaProjects/FinalProjectYuriev/src/resources/currency");*/
    }

    @Override
    public String toString() {
        return "CurrencyReader{" +
                exchangeCurrency +
                '}';
    }
}
