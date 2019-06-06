package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PortfolioPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";

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
}
