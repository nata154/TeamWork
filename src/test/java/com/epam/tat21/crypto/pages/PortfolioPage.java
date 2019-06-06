package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.exceptions.NoSuchPortfolioException;
import com.epam.tat21.crypto.service.TestDataReader;
<<<<<<< HEAD
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
=======
import com.epam.tat21.crypto.utils.PortfolioKeeper;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.concurrent.TimeUnit;
>>>>>>> Posakhau

public class PortfolioPage extends HeaderPage {


    private String waitingStubElementXpath = "//md-icon[@aria-label='Add new coin to portfolio']";

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    public PortfolioKeeper portfolioKeeper = new PortfolioKeeper();

    @FindBy(xpath = "//button[@ng-click='addPortfolioDialog()']")
    private WebElement buttonAddPortfolio;

    @FindBy(xpath = "//span[@class='ng-scope' and contains(text(), 'Coin')]")
    private WebElement addCoinToPortfolioButton;

    @FindBy(xpath = "//div[@class='list-table list-portfolio padding-5']")
    private WebElement tableOfCoinsInPortfolio;

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PortfolioPage openPage() {
        driver.get(BASE_URL);
<<<<<<< HEAD
        MyLogger.info("PortfolioPage was opened");
        return this;
    }

    public AddCoinForm getAddCoinForm() {
        waitForElementClicable(addCoinToPortfolioButton);
        addCoinToPortfolioButton.click();
        MyLogger.info("AddCoinForm was appeared");
        return new AddCoinForm(driver);
    }

    public AddPortfolioForm addPortfolioForm() {
        waitForElementClicable(buttonAddPortfolio);
        buttonAddPortfolio.click();
        MyLogger.info("AddPortfolioForm was appeared");
        return new AddPortfolioForm(driver);
    }
=======

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

>>>>>>> Posakhau
}
