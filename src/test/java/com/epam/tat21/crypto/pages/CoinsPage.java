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

    private static final String CURRENCY_LINE_XPATH = "//ul[@class='nav nav-tabs nav-coinss']//a[contains(text(), '";
    private static final String COIN_IN_COLUMN_XPATH = "//tr[@class='ng-scope']/td[@data-href='/coins/";

    @FindBy(xpath = "//span[@class='mobile-name ng-binding' and contains(text(), 'BTC')]/..")
    private WebElement bitcoinLineAtPage;

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
        Map<String, Map<String, Double>> coinCurrencyMap = new LinkedHashMap<>();
        for (int j = 0; j < coins.size(); j++) {//for each coin
            Map<String, Double> currencyForEachCoinMap = new LinkedHashMap<>();
            for (int i = 0; i < currency.size(); i++) {//select tab currency, for example EUR
                Double parsedValueOfCoin = readPriceOfCoin(coins.get(j), currency.get(i));
                currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), parsedValueOfCoin);
            }
            coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
        }
        return coinCurrencyMap;
    }

    public Double readPriceOfCoin(Coin coin, Currency currency) {
        WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.getNameOfCurrency() + "')]"));
        waitForElementClickable(tabCurrency);
        scrollPage(tabCurrency);
        tabCurrency.click();
        waitForElementVisible(bitcoinLineAtPage);//wait for loading page
        WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                coin.getAbbreviationCoin().toLowerCase() + "/overview/" + currency.getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
        waitForElementClickable(lineCoinFieldCost);
        lineCoinFieldCost.click();//click coin at tab of currency and get its value
        String currentCostOfCoin = lineCoinFieldCost.getText();
        return CoinInformationParser.parseTotalCoinValue(currentCostOfCoin);
    }

}



