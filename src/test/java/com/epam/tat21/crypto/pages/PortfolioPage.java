package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.PortfolioKeeper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;

public class PortfolioPage extends BasePage {


    private String waitingStubElementXpath = "//md-icon[@aria-label='Add new coin to portfolio']";

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
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

        double totalValue = 0;
        portfolioKeeper.initiate(driver);

        try {

            totalValue = portfolioKeeper.getTotalValue(name.toUpperCase());
        } catch (NoSuchPortfolioException e) {
            System.out.println(e.getMessage());
        }
        return totalValue;
    }

    protected static WebElement waitForElementBeClickableLocatedByXpath(WebDriver driver, String xPath) {
        return new WebDriverWait(driver, 20).until(ExpectedConditions.elementToBeClickable(By.xpath(xPath)));
    }

}
