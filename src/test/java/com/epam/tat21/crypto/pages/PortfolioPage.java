package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.PortfolioKeeper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class PortfolioPage extends BasePage {

    private String portfolioElementXpath = "//md-tab-item";
    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    public PortfolioKeeper portfolioKeeper = new PortfolioKeeper();

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PortfolioPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public double getPortfolioTotalValue(String name) {
        double totalValue = 0;
        portfolioKeeper.initiate(driver);
        try {
            totalValue = portfolioKeeper.getTotalValue(name);
        } catch (NoSuchPortfolioException e) {
            System.out.println(e.getMessage());
        }
        return totalValue;
    }

    public List<WebElement> getAllPortfolioTabs() {
        return driver.findElements(By.xpath(portfolioElementXpath));
    }

}
