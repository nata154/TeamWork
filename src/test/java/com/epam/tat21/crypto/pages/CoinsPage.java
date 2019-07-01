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
    private static final String ACTIVE_CURRENCIES_TAB_FOR_WAIT_XPATH = "//li[@class='ng-scope active']/a[contains(text(), '";
    private static final String BITCOIN_LINE_FOR_WAIT_XPATH = "//span[@class='mobile-name ng-binding' and contains(text(), '";
//span[@class='mobile-name ng-binding' and contains(text(), 'BTC')]

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
        Map<String, Map<String, Double>> coinCurrencyMap = new LinkedHashMap<>();
        for (int j = 0; j < coins.size(); j++) {//for each coin
            Map<String, Double> currencyForEachCoinMap = new LinkedHashMap<>();
            for (int i = 0; i < currency.size(); i++) {//select tab currency, for example EUR
                waitForElementClickable(priceColumn);
                WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
                waitForElementClickable(tabCurrency);
                scrollPage(tabCurrency);
                tabCurrency.click();

                WebElement tabActiveCurrency = driver.findElement(By.xpath(ACTIVE_CURRENCIES_TAB_FOR_WAIT_XPATH + currency.get(i).getNameOfCurrency() + "')]"));// wait loading of page
                waitForElementClickable(tabActiveCurrency);

                //driver.manage().timeouts().implicitlyWait(1, TimeUnit.SECONDS);
                // waitForElementClickable(priceColumn);
                //scrollPage(tabCurrency);
                WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
                        coins.get(j).getAbbreviationCoin().toLowerCase() + "/overview/" + currency.get(i).getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
                //tr[@class='ng-scope']/td[@data-href='/coins/xmr/overview/EUR']/../td[starts-with(@class, 'price')]/div
                //scrollPage(lineCoinFieldCost);

                waitForElementClickable(lineCoinFieldCost);//here we click coin at tab of currency and get its value
                lineCoinFieldCost.click();
                String currentCostOfCoin = lineCoinFieldCost.getText();
                Double parsedValueOfCoin = CoinInformationParser.parseTotalCoinValue(currentCostOfCoin);
                //MyLogger.info("Currency " + currency.get(i).getNameOfCurrency() + " line for coin " + coins.get(j).getAbbreviationCoin() + " was selected and price is " + parsedValueOfCoin + ".");
                currencyForEachCoinMap.put(currency.get(i).getNameOfCurrency(), parsedValueOfCoin);
            }
            coinCurrencyMap.put(coins.get(j).getAbbreviationCoin(), currencyForEachCoinMap);
            }
        return coinCurrencyMap;
    }

}



