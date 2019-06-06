package com.epam.tat21.crypto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SimplePortfolio {

    private String coinNameXpath = "//a[contains(@class,'ng-binding')]|//a[contains(@href,'coins')]|a[contains(@ng-ref,'overview')]";
    private String valueXpath = "//span[contains(@title,'Total')]";

    private ArrayList<String>coinNames = new ArrayList<>();
    private ArrayList<Double>coinValues = new ArrayList<>();

    public SimplePortfolio (String portfolioName, WebElement portfolioLink) {
        setPortfolioName(portfolioName);
        setPortfolioLink(portfolioLink);
    }

    private WebElement portfolioLink = null;

    private String portfolioName="default portfolio";

    private ArrayList<SimpleCoin>coinsInPortfolio = new ArrayList<>();

    private CoinParser coinParser = new CoinParser();

    public void initiate(WebDriver driver) {

        List<WebElement>coins = driver.findElements(By.xpath(coinNameXpath));

        for (int i=0;i<coins.size();++i) {
            if (coins.get(i).getText().length() > 5) {
                coinNames.add(coins.get(i).getText());
            }
        }

        List<WebElement>values = driver.findElements(By.xpath(valueXpath));
        for (int i = 0; i < values.size(); ++i) {
            coinValues.add(coinParser.parseTotalCoinValue(values.get(i).getText()));
        }
        formTheCoinsStack();
    }

    public String getPortfolioName() {
        return portfolioName;
    }

    public void setPortfolioName(String portfolioName) {
        this.portfolioName = portfolioName;
    }

    public WebElement getPortfolioLink() {
        return portfolioLink;
    }

    public void setPortfolioLink(WebElement portfolioLink) {
        this.portfolioLink = portfolioLink;
    }

    public double getTotalValue() {
        double totalValue = 0;
        if (coinsInPortfolio.size() > 0) {
            for (int i = 0; i < coinsInPortfolio.size(); ++i) {
                totalValue += coinsInPortfolio.get(i).getCoinTotalValue();
            }
        }
        return totalValue;
    }

    public void formTheCoinsStack() {
        for (int i=0;i<coinValues.size()-1;++i) {
            SimpleCoin coin = new SimpleCoin(coinNames.get(i),coinValues.get(i+1));
            coinsInPortfolio.add(coin);
        }
    }

    public void printPortfolioReport() {
        for (int i=0;i<coinsInPortfolio.size();++i) {
           coinsInPortfolio.get(i).coinReport();
        }
    }

}
