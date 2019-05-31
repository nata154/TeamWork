package com.epam.tat21.crypto.utils;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PortfolioKeeper {

    private WebDriver driver;

    private String portfolioElementTagName = "md-tab-item";

    public PortfolioKeeper() {}

    private ArrayList<SimplePortfolio>portfoliosList = new ArrayList<>();

    public void initiate(WebDriver driver) {

        this.driver = driver;

        //System.out.println("Portfolio Keeper initiated....");

        WebElement waitingStub = waitForElementBeClickableLocatedByXpath(driver,"//a[contains(.,'Read our guide: Portfolio FAQ')]");

        List<WebElement> portfolioElements = driver.findElements(By.tagName(portfolioElementTagName));
        //System.out.println("AMOUNT OF PORTFOLIOS: - "+portfolioElements.size());

        //System.out.println("Taking names.....");

        for (int i=0;i<portfolioElements.size();++i) {
            SimplePortfolio portfolio = new SimplePortfolio(portfolioElements.get(i).getText(),portfolioElements.get(i));
            portfoliosList.add(portfolio);
            //System.out.println(portfolioElements.get(i).getText());
        }
    }

    public double getTotalValue(String portfolioName) {
        double totalValue =0;
        for (int i=0;i<portfoliosList.size();++i) {
            if (portfolioName.equals(portfoliosList.get(i).getPortfolioName())) {
                //System.out.println("PORTFOLIO "+ portfoliosList.get(i).getPortfolioName() + " HAS BEEN CHOOSES TO OPERATE ON POSITION "+(i+1));
                    portfoliosList.get(i).getPortfolioLink().click();
                    portfoliosList.get(i).initiate(driver);
                    totalValue = portfoliosList.get(i).getTotalValue();
            }
        }

        return totalValue;
    }

    protected static WebElement waitForElementBeClickableLocatedByXpath(WebDriver driver, String xPath) {
        return new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }
}
