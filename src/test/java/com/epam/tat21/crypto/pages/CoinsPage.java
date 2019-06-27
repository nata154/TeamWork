package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.CoinInformationParser;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Iterator;
import java.util.LinkedHashMap;
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


    public Map<String, Map<String, Double>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
        Map<String, Map<String, Double>> coinCurrencyMap = new LinkedHashMap<String, Map<String, Double>>();

        for (int j = 0; j < coins.size(); j++) {//for each coin
            Map<String, Double> currencyForEachCoinMap = new LinkedHashMap<String, Double>();
            for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
                WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
                waitForElementClickable(tabCurrency);
                tabCurrency.click();
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                        coins.get(j).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
                waitForElementClickable(lineCoinFieldCost);//here we click coin at tab of currency and get its value
                lineCoinFieldCost.click();
                MyLogger.info("Currency line for coin " + coins.get(j).getAbbreviationCoin() + " was selected");

                String currentCostOfCoin = lineCoinFieldCost.getText();
                Double parsedValueOfCoin = CoinInformationParser.parseCurrenciesForCoins(currentCostOfCoin);
                System.out.println(parsedValueOfCoin);

                currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), parsedValueOfCoin);
            }
            coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
            }
        System.out.println("-----/Result map------");
        printMap(coinCurrencyMap);
        return coinCurrencyMap;
        }


    public void printMap(Map<String, Map<String, Double>> coinCurrencyMap) {
        Iterator it = coinCurrencyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }

    public void printMap2(Map<String, String> currencyForEachCoinMap) {
        Iterator it = currencyForEachCoinMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
        }
    }


//    public Map<String, Map<Currency, String>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
//        for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
//            WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
//            waitForElementClickable(tabCurrency);
//            tabCurrency.click();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int j = 0; j < coins.size(); j++) {//here we find our lines with coins (at this currency tab) and get this sum
//                WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
//                        coins.get(j).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
//                waitForElementClickable(lineCoinFieldCost);
//                lineCoinFieldCost.click();
//                MyLogger.info("Currency line for coin " + coins.get(j).getAbbreviationCoin() + " was selected");
//                String currentCostOfCoin = lineCoinFieldCost.getText().substring(2);
//                System.out.println(currentCostOfCoin);
//                currencyForEachCoinMap.put(currency.get(i), currentCostOfCoin);
//                coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
//                //printMap(currencyForEachCoinMap);
//                coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
//            }
//            //coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
//        }
//        printMap(coinCurrencyMap);
//        return coinCurrencyMap;
//    }


//    public Map<String, Map<String, String>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
//        Map<String, Map<String, String>> coinCurrencyMap = new LinkedHashMap<String, Map<String, String>>();
//        Map<String, String> currencyForEachCoinMap;
//        for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
//
//            WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
//            waitForElementClickable(tabCurrency);
//            tabCurrency.click();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            for (int j = 0; j < coins.size(); j++) {//here we find our lines with coins (at this currency tab) and get this sum
//                currencyForEachCoinMap = new LinkedHashMap<String, String>();
//                // coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
//                WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
//                        coins.get(j).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
//                waitForElementClickable(lineCoinFieldCost);
//                lineCoinFieldCost.click();
//                MyLogger.info("Currency line for coin " + coins.get(j).getAbbreviationCoin() + " was selected");
//                String currentCostOfCoin = lineCoinFieldCost.getText().substring(2);
//                System.out.println(currentCostOfCoin);
//
//                currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), currentCostOfCoin);
//                coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
//            }
//        }
//        System.out.println("-----/Result map------");
//        printMap(coinCurrencyMap);
//        return coinCurrencyMap;
//    }
}



