package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.CoinInformationParser;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CoinsPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "coins/list/";

    private final static String CURRENCY_LINE_XPATH = "//ul[@class='nav nav-tabs nav-coinss']//a[contains(text(), '";
    private final static String COIN_IN_COLUMN_XPATH = "//tr[@class='ng-scope']/td[@data-href='/coins/";

    @FindBy(xpath = "//a[@class='btn btn-xs btn-switch ng-scope']")
    private WebElement nextPageAtCoin;

    @FindBy(xpath = "//th[@class='ng-binding ng-scope price']")
    private WebElement priceColumn;

    public CoinsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoinsPage openPage() {
        driver.get(BASE_URL);
        MyLogger.info("CoinsPage was opened");
        return this;
    }

    public CoinsPage scrollPage(WebElement webElement) {
        Actions action = new Actions(driver);
        action.moveToElement(webElement).build().perform();
        return this;
    }


    public Map<String, Map<String, Double>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
        Map<String, Map<String, Double>> coinCurrencyMap = new LinkedHashMap<String, Map<String, Double>>();

        for (int j = 0; j < coins.size(); j++) {//for each coin
            Map<String, Double> currencyForEachCoinMap = new LinkedHashMap<String, Double>();
            for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
                waitForElementClickable(priceColumn);
                WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
                waitForElementClickable(tabCurrency);
                scrollPage(tabCurrency);
                tabCurrency.click();

                //waitForElementClickable(nextPageAtCoin);
                waitForElementClickable(priceColumn);
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
//        System.out.println("-----/Result map------");
//        printCoinsMap(coinCurrencyMap);
        return coinCurrencyMap;
    }    //этот работает


//    public void printCoinsMap(Map<String, Map<String, Double>> coinCurrencyMap) {
//        Iterator it = coinCurrencyMap.entrySet().iterator();
//        while (it.hasNext()) {
//            Map.Entry pair = (Map.Entry) it.next();
//            System.out.println(pair.getKey() + " = " + pair.getValue());
//        }
//    }

//    public Map<String, Map<String, Double>> selectCurrencyAndGetCostForCoins1(List<Coin> coins, List<Currency> currency) {
//        Map<String, Map<String, Double>> coinCurrencyMap = new LinkedHashMap<>();
//        for (int i = 0; i < currency.size(); i++) {//here we select tab currency, for exmpl EUR
//            Map<String, Double> currencyForEachCoinMap = new LinkedHashMap<String, Double>();
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
//                double currentCostOfCoin = CoinInformationParser.parseCurrenciesForCoins(lineCoinFieldCost.getText());
//
//                //currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), currentCostOfCoin);
//                                currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), currentCostOfCoin);
//                coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
//                coinCurrencyMap.get(coins.get(j).getAbbreviationCoin()).put(currency.get(i).getNameOfCurrency(), currentCostOfCoin);
//            }
//            //coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
//        }
//        System.out.println("-----/Result map------");
//        printCoinsMap(coinCurrencyMap);
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
//        printCoinsMap(coinCurrencyMap);
//        return coinCurrencyMap;
//    }
}



