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

    Map<String, Map<Currency, String>> coinCurrencyMap;
    Map<Currency, String> currencyForEachCoinMap;

    public Map<String, Map<Currency, String>> selectCurrencyAndGetCostForCoins(List<Coin> coins, List<Currency> currency) {
        for (int i = 0; i < currency.size(); i++) {
            coinCurrencyMap = new HashMap<String, Map<Currency, String>>();
            currencyForEachCoinMap = new HashMap<>();

            WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
            waitForElementClickable(tabCurrency);
            tabCurrency.click();

            WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                    coins.get(i).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
            waitForElementVisible(lineCoinFieldCost);
            lineCoinFieldCost.click();
            MyLogger.info("Currency line for coin " + coins.get(i).getAbbreviationCoin() + " was selected");
            String currentCostOfCoin = lineCoinFieldCost.getText();
            System.out.println(currentCostOfCoin);

            currencyForEachCoinMap.put(currency.get(i), currentCostOfCoin);
            coinCurrencyMap.put(coins.get(i).getAbbreviationCoin(), currencyForEachCoinMap);
        }
        printMap(coinCurrencyMap);
        return coinCurrencyMap;
    }

    public void printMap(Map<String, Map<Currency, String>> coinCurrencyMap) {
        Iterator it = coinCurrencyMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry pair = (Map.Entry) it.next();
            System.out.println(pair.getKey() + " = " + pair.getValue());
//            it.remove(); // avoids a ConcurrentModificationException
        }
    }

//    public int getNumberOfNewsForCoin(Coin coin) {
//        int currentCountOfNews = 0;
//        waitForNewsVisibility(coin.getAbbreviationCoin());
//        List<WebElement> news = containerOfNews.findElements(By.id("news_"));
//        String xpathCategoriesOfNews = "//span[contains(text(),'";
//        for (WebElement item : news) {
//            waitForElementVisible(item);
//            if (item.findElement(By.xpath(xpathCategoriesOfNews + coin.getAbbreviationCoin() + "')]")).isEnabled()) {
//                currentCountOfNews++;
//            }
//        }
//        return currentCountOfNews;
//    }

    public String findCurrencyCostForCoin(Coin coin, Currency currency) {
        WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                coin.getAbbreviationCoin().toLowerCase() + "/overview/" + currency.getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        waitForElementVisible(lineCoinFieldCost);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        lineCoinFieldCost.click();
        MyLogger.info("Currency line for coin was selected");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        String currentCostOfCoin = lineCoinFieldCost.getText();
        System.out.println(currentCostOfCoin);
        return currentCostOfCoin;
    }
}



