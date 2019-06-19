package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

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

    public CoinsPage selectCurrency(List<Currency> currency) {
        for (int i = 0; i < currency.size(); i++) {
            WebElement tabCurrency = driver.findElement(By.xpath(CURRENCY_LINE_XPATH + currency.get(i).getNameOfCurrency() + "')]"));
            waitForElementClickable(tabCurrency);
            tabCurrency.click();
            return this;
        }
        return this;
    }

//        public int getNumberOfNewsForCoin (Coin coin){
//            int currentCountOfNews = 0;
//            waitForNewsVisibility(coin.getAbbreviationCoin());
//            List<WebElement> news = containerOfNews.findElements(By.id("news_"));
//            String xpathCategoriesOfNews = "//span[contains(text(),'";
//            for (WebElement item : news) {
//                waitForElementVisible(item);
//                if (item.findElement(By.xpath(xpathCategoriesOfNews + coin.getAbbreviationCoin() + "')]")).isEnabled()) {
//                    currentCountOfNews++;
//                }
//            }
//            return currentCountOfNews;
//        }
//
//        public String findCurrencyCostForCoin (Coin coin, Currency currency){
//            WebElement lineCoinFieldCost = driver.findElement(By.xpath(COIN_IN_COLUMN_XPATH +
//                    coin.getAbbreviationCoin().toLowerCase() + "/overview/" + currency.getNameOfCurrency() + "']/../td[starts-with(@class, 'price')]/div"));
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            waitForElementVisible(lineCoinFieldCost);
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            lineCoinFieldCost.click();
//            MyLogger.info("Currency line for coin was selected");
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            String currentCostOfCoin = lineCoinFieldCost.getText();
//            System.out.println(currentCostOfCoin);
//            return currentCostOfCoin;
//        }
    }



