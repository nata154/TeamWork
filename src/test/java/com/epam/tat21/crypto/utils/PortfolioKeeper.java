package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class PortfolioKeeper {

    private WebDriver driver;
    private String portfolioElementXpath = "//md-tab-item";

    public PortfolioKeeper() {}

    private ArrayList<SimplePortfolio>portfoliosList = new ArrayList<>();

    public void initiate(WebDriver driver) {
        this.driver = driver;
        List<WebElement> portfolioElements = driver.findElements(By.xpath(portfolioElementXpath));

        for (int i = 0; i < portfolioElements.size(); ++i) {
            SimplePortfolio portfolio = new SimplePortfolio(portfolioElements.get(i).getText(), portfolioElements.get(i));
            portfoliosList.add(portfolio);
        }
    }

    public double getTotalValue(String portfolioName) throws NoSuchPortfolioException {
        if (isCurrentPortfolioExists(portfolioName)) {
            double totalValue = 0;
            for (int i = 0; i < portfoliosList.size(); ++i) {
                if (portfolioName.equals(portfoliosList.get(i).getPortfolioName())) {
                    portfoliosList.get(i).getPortfolioLink().click();
                    portfoliosList.get(i).initiate(driver);
                    totalValue = portfoliosList.get(i).getTotalValue();
                }
            }
            return totalValue;
        } else throw new NoSuchPortfolioException();
    }

    private boolean isCurrentPortfolioExists(String currentPortfolioName) {
        int matchesNumber = 0;
        for (int i = 0; i < portfoliosList.size(); ++i) {
            if (currentPortfolioName.equals(portfoliosList.get(i).getPortfolioName())) {
                matchesNumber++;
            }
        }
        if (matchesNumber > 0) {
            return true;
        } else return false;
    }

    protected static WebElement waitForElementBeClickableLocatedByXpath(WebDriver driver, String xPath) {
        return new WebDriverWait(driver,20).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }
}
