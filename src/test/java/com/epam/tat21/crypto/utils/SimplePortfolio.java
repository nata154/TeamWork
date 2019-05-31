package com.epam.tat21.crypto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class SimplePortfolio {

    private String coinContainerXpath = "//div[@class='table-row ng-scope']";
    private String coinNameXpath = "//a[contains(@ng-ref,'/overview/')]|//a[contains(@href,'/coins/')]";
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

    public void initiate(WebDriver driver) {

        List<WebElement>coinContainers = driver.findElements(By.xpath(coinContainerXpath));
        //System.out.println("THERE ARE "+coinContainers.size()+" COINS IN CURRENT PORTFOLIO" );

        List<WebElement>coins = driver.findElements(By.xpath(coinNameXpath));
        //System.out.println("COINS LIST: "+coins.size());
        for (int i=0;i<coins.size();++i) {
            if (coins.get(i).getText().length()>5) {
                //System.out.println("COIN NAME-"+coins.get(i).getText()+"-");
                coinNames.add(coins.get(i).getText());
            }
        }

        List<WebElement>values = driver.findElements(By.xpath(valueXpath));
        for (int i=0;i<values.size();++i) {
                //System.out.println("COIN VALUE ELEMENT -"+i +"-"+ parseTotalCoinValue(values.get(i).getText())+ "- ");
                coinValues.add(parseTotalCoinValue(values.get(i).getText()));
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
        double totalValue =0;
        for (int i=0;i<coinsInPortfolio.size();++i) {
            totalValue+=coinsInPortfolio.get(i).getCoinTotalValue();
        }
        return totalValue;
    }

    public double parseTotalCoinValue(String valueExpression) {
        double finalValue = 0;
        if (valueExpression.length()>1) {

            String lastSymbol = valueExpression.substring(valueExpression.length()-1);
            //System.out.println("LAST SYMBOL -"+lastSymbol+"-");

            String clearValueString = valueExpression.substring(2,valueExpression.length()-2);
            //System.out.println("CLEAR VALUE -"+clearValueString+"-");

            finalValue = Double.parseDouble(clearValueString);
            //System.out.println("CLEAR VALUE DOUBLE ="+clearValueString+"=");

            switch (lastSymbol) {

                case "M": {
                    finalValue = finalValue*1000000;
                    break;
                }

                case "k": {
                    finalValue = finalValue*1000;
                    break;
                }

                default: {}
            }
        } else {
            System.out.println();
        }

        return finalValue;
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
