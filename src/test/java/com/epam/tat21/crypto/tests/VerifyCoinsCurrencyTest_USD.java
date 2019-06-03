package com.epam.tat21.crypto.tests;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.List;

public class VerifyCoinsCurrencyTest_USD {

    private static String testURL = "https://www.cryptocompare.com/coins/list/USD/1";

    private int nonCompareCurrencyCounter =0;
    private static String currencySymbol = "$";
    private static String currencyName = "USD";

    private String coinCurrencyFieldLocatorXpath = "//div[contains(@class,'current-price-value')]";

    private WebDriver driverPrivate = new FirefoxDriver();

    @BeforeTest
    public void setInitialState () {
        driverPrivate.get(testURL);
        driverPrivate.manage().window().maximize();
    }

    @Test
    public void verifyCoinsCurrencyTest_USD() {
        System.out.println("COMPARE TEST");
        verifyAllCoinsCurrency();
        Assert.assertEquals(getNonCompareCurrencyCounter(),0);
    }

    private void verifyAllCoinsCurrency() {

        int counter =0;
        List<WebElement>coinPriceElementsList = driverPrivate.findElements(By.xpath(coinCurrencyFieldLocatorXpath));
        System.out.println("AMOUNT OF COINS - "+coinPriceElementsList.size());
        System.out.println("=================================================");
        System.out.println("COIN PRICES:");
        System.out.println("=================================================");

        for (int i=0;i<coinPriceElementsList.size();++i) {

            System.out.println("-------------");
            System.out.println(coinPriceElementsList.get(i).getText());
            if (!coinPriceElementsList.get(i).getText().substring(0,1).equals(currencySymbol)) {
                setNonCompareCurrencyCounter(counter++);
            }
        }
    }

    public void setNonCompareCurrencyCounter(int nonCompareCurrencyCounter) {
        this.nonCompareCurrencyCounter = nonCompareCurrencyCounter;
    }

    public int getNonCompareCurrencyCounter() {
        return nonCompareCurrencyCounter;
    }

}
