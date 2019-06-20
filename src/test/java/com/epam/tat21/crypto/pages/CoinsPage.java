package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CoinsPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "coins/list/";

    private final static String CURRENCY_LINE_XPATH = "//ul[@class='nav nav-tabs nav-coinss']//a[contains(text(), '";
    private final static String COIN_IN_COLUMN_XPATH = "//tr[@class='ng-scope']/td[@data-href='/coins/";

    //tr[@class='ng-scope']/td[@data-href='/coins/btc/overview/USD']/../td[starts-with(@class, 'price')]

    public CoinsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoinsPage openPage() {
        driver.get(BASE_URL);
        MyLogger.info("CoinsPage was opened");
        return this;
    }

    Map<String, Map<Currency, String>> coinCurrencyMap = new HashMap<String, Map<Currency, String>>();
    Map<Currency, String> currencyForEachCoinMap = new HashMap<>();

    public Map<String, Map<Currency, String>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
        for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
            WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
            waitForElementClickable(tabCurrency);
            tabCurrency.click();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            for (int j = 0; j < coins.size(); j++) {//here we find our lines with coins (at this currency tab) and get this sum
                WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                        coins.get(j).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
                waitForElementClickable(lineCoinFieldCost);
                lineCoinFieldCost.click();
                MyLogger.info("Currency line for coin " + coins.get(j).getAbbreviationCoin() + " was selected");
                String currentCostOfCoin = lineCoinFieldCost.getText().substring(3);
                System.out.println(currentCostOfCoin);
                currencyForEachCoinMap.put(currency.get(j), currentCostOfCoin);
            }
            coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
        }
        printMap(coinCurrencyMap);
        return coinCurrencyMap;
    }

    public void printMap(Map<String, Map<Currency, String>> coinCurrencyMap) {
        Iterator it = this.coinCurrencyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }

    public void generateStringArrayFromMap(Map<String, Map<Currency, String>> coinCurrencyMap) {
        String[] mapAsArray = new String[coinCurrencyMap.size()];
        Iterator it = this.coinCurrencyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            //  mapAsArray.
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }

}



