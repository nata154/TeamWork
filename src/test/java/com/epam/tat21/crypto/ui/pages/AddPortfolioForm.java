package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.service.TestDataReader;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddPortfolioForm extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    private static final String PORTFOLIO_CURRENCY_LOCATOR = "(//md-option[@value='%s']/div[1]/span)[2]";

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private WebElement inputPortfolioName;

    @FindBy(xpath = "//md-select[@name='currency']//span")
    private WebElement dropdownCurrency;

    @FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
    private WebElement textareaDescription;

    @FindBy(xpath = "//span[text()=' Private Portfolio ']")
    private WebElement radiobuttonPrivatePortfolio;

    @FindBy(xpath = "//span[text()=' Public Portfolio ']")
    private WebElement radiobuttonPublicPortfolio;

    @FindBy(xpath = "//span[contains(text(), 'Create')]")
    private WebElement buttonCreate;

    @FindBy(xpath = "//md-icon[@aria-label='Close dialog']")
    private WebElement buttonCloseFormAddPortfolio;

    @FindBy(xpath = "//span[contains(text(), 'Update')]")
    private WebElement buttonUpdatePortfolio;

    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
    private WebElement buttonDeletePortfolio;

    @FindBy(xpath = "(//div[contains(@id,'select_container')])[2]")
    private WebElement portfolioCurrencyDropdownMenu;

    public AddPortfolioForm(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddPortfolioForm openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public PortfolioPage createNewPortfolio(String name, String currency, String description) {
        waitForElementVisible(inputPortfolioName);
        Actions action = new Actions(driver);
        action.sendKeys(inputPortfolioName, name).build().perform();
        dropdownCurrency.click();
        new WebDriverWait(driver, 5).until(ExpectedConditions
                .attributeToBe(portfolioCurrencyDropdownMenu, "class", "md-select-menu-container md-active md-clickable"));
        WebElement portfolioCurrency = driver.findElement(By
                .xpath(String.format(PORTFOLIO_CURRENCY_LOCATOR, currency)));
        waitForElementClickable(portfolioCurrency);
        portfolioCurrency.click();
        textareaDescription.sendKeys(description);
        waitForElementClickable(buttonCreate);
        buttonCreate.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage editUserPortfolio(String name) {
        waitForElementVisible(inputPortfolioName);
        inputPortfolioName.sendKeys(name);
        waitForElementClickable(buttonUpdatePortfolio);
        buttonUpdatePortfolio.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage deleteUserPortfolio() {
        waitForElementClickable(buttonDeletePortfolio);
        buttonDeletePortfolio.click();
        return new PortfolioPage(driver);
    }
}
