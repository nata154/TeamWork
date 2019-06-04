package com.epam.tat21.crypto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SimplePortfolio {

    private String elements = "#tab-content-1 > div:nth-child(1) > md-content:nth-child(1) > div:nth-child(2) > portfolio-coins:nth-child(2)>*";


    private String coinContainerXpath = "//div[@class='table-row ng-scope']|div[contains(@ng-repeat,'(index,portfolioMember) in getCurrentMembers()')]";

    /*
    <a ng-href="/coins/btc/overview/" class="ng-binding" href="/coins/btc/overview/">Bitcoin (BTC)</a>

    #tab-content-1 > div:nth-child(1) > md-content:nth-child(1) > div:nth-child(2) > portfolio-coins:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > div:nth-child(2) > a:nth-child(1)

    * */


    private String coinNameXpath = "//a[contains(@class,'ng-binding')]|//a[contains(@href,'coins')]|a[contains(@ng-ref,'overview')]";
    //private String coinNameCSS_Selector = "a[class^='ng-binding']|a[ng-ref^='/coins/']|a[href^='/overview/']";
    private String coinNameCSS_Selector = "a[class^='ng-binding']";


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

        List<WebElement> list1 = driver.findElements(By.cssSelector(elements));
        System.out.println("TABLE ELEMENTS AMOUNT - " + list1.size());

        List<WebElement> cNames = driver.findElements(By.cssSelector(coinNameCSS_Selector));

        for (int i = 0; i < cNames.size(); ++i) {
            System.out.println("CSS NAMES - " + cNames.get(i).getText());
        }









        /*-------------------------------------------------------*/


        List<WebElement>coinContainers = driver.findElements(By.xpath(coinContainerXpath));
        System.out.println("THERE ARE " + coinContainers.size() + " COIN CONTAINERS IN CURRENT PORTFOLIO");

        List<WebElement>coins = driver.findElements(By.xpath(coinNameXpath));
        System.out.println("COINS LIST: " + coins.size());
        for (int i=0;i<coins.size();++i) {
            if (coins.get(i).getText().length()>5) {
                //System.out.println("COIN NAME-" + coins.get(i).getText() + "-");
                coinNames.add(coins.get(i).getText());
            }
        }

        List<WebElement>values = driver.findElements(By.xpath(valueXpath));
        for (int i=0;i<values.size();++i) {
            System.out.println("COIN VALUE ELEMENT -" + i + "-" + coinParser.parseTotalCoinValue(values.get(i).getText()) + "- ");
            coinValues.add(coinParser.parseTotalCoinValue(values.get(i).getText()));
        }

        //        System.out.println("COIN NAMES LIST HAVE "+coinNames.size()+" ITEMS");
//        for (int i=0;i<coinNames.size();++i) {
//            System.out.println("NAME OF ELEMENT "+i+" - "+coinNames.get(i));
//        }

//        System.out.println("COIN VALUES LIST HAVE "+coinValues.size()+" ITEMS");
//        for (int i=0;i<coinValues.size();++i) {
//            System.out.println("NAME OF ELEMENT "+i+" - "+coinValues.get(i));
//        }

        formTheCoinsStack();
        printPortfolioReport();

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
        double totalValue = coinsInPortfolio.get(0).getCoinTotalValue();
        for (int i=0;i<coinsInPortfolio.size();++i) {
            System.out.println("ADDING +" + coinsInPortfolio.get(i).getCoinTotalValue());
            totalValue+=coinsInPortfolio.get(i).getCoinTotalValue();
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
