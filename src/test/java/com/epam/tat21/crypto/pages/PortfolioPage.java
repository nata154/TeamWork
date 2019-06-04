package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
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
        return this;
    }

    public AddCoinForm getAddCoinForm() {
        waitForElementClicable(addCoinToPortfolioButton);
        addCoinToPortfolioButton.click();
        return new AddCoinForm(driver);
    }

    public AddPortfolioForm addPortfolioForm() {
        waitForElementClicable(buttonAddPortfolio);
        buttonAddPortfolio.click();
        return new AddPortfolioForm(driver);
    }
}
