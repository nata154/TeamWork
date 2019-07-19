package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class AddPortfolioForm extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    private static final String PORTFOLIO_CURRENCY_LOCATOR = "(//md-option[@value='%s']/div[1]/span)[2]";

    @FindBy(xpath = "//input[@ng-model='newPortfolio.Name']")
    private WebElement inputPortfolioName;

    @FindBy(xpath = "(//div[contains(@id,'select_container')])[2]")
    private WebElement portfolioCurrencyDropdown;

    @FindBy(xpath = "//md-select[@name='currency']//span")
    private WebElement dropdownCurrency;

    @FindBy(xpath = "//textarea[@ng-model='newPortfolio.Description']")
    private WebElement textAreaDescription;

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

    public AddPortfolioForm(WebDriver driver) {
        super(driver);
    }

    @Override
    public AddPortfolioForm openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public WebElement getElementCurrency(String currency) {
        String xpathForGetCurrency = "(//md-option[@value='" + currency + "']/div[1]/span)[2]/../..";
        return driver.findElement(By.xpath(xpathForGetCurrency));
    }

    public PortfolioPage scroll(WebElement element) {
        Actions action = new Actions(driver);
        action.moveToElement(element).build().perform();
        return new PortfolioPage(driver);
    }

    private String readValueForElement(WebElement element) {
        JavascriptExecutor js = (JavascriptExecutor) driver;
        String jsRequest = "return arguments[0].value;";
        String actualValue = (String) js.executeScript(jsRequest, element);
        MyLogger.info("get inputed name from line - " + actualValue);
        return actualValue;
    }

    private AddPortfolioForm actionSendKeys(WebElement element, String expectedName) {
        Actions action = new Actions(driver);
        action.sendKeys(element, expectedName).build().perform();
        return this;
    }

    private String getPortfolioName() {
        waitForElementVisible(inputPortfolioName);
        return readValueForElement(inputPortfolioName);
    }

    private void assurePortfolioName(String actualName, String expectedName) {
        if (actualName.equals(expectedName)) {
            MyLogger.info("Inputed name of portfolio is correct and contains all letters.");
        } else {
                MyLogger.info("Wrong portfolio name. Trying to reinput it.");
                inputPortfolioName.clear();
                actionSendKeys(inputPortfolioName, expectedName);
                readValueForElement(inputPortfolioName);
        }
    }

    public PortfolioPage createNewPortfolio(String name, String currency, String description) {
        waitForElementVisible(inputPortfolioName);
        inputPortfolioName.click();
        inputPortfolioName.sendKeys(name);
        assurePortfolioName(getPortfolioName(), name);
        dropdownCurrency.click();
        //solving locating of dropdownMenu. waiting of special attribute.
        WaitConditions.waitForAttributeToBe(driver, portfolioCurrencyDropdown, "class", "md-select-menu-container md-active md-clickable");
        WebElement portfolioCurrency = driver.findElement(By.xpath(String.format(PORTFOLIO_CURRENCY_LOCATOR, currency)));
        waitForElementClickable(portfolioCurrency);
        portfolioCurrency.click();
        textAreaDescription.sendKeys(description);
        waitForElementClickable(buttonCreate);
        buttonCreate.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage editUserPortfolio(String name) {
        waitForElementVisible(inputPortfolioName);
        inputPortfolioName.clear();
        inputPortfolioName.sendKeys(name);
        assurePortfolioName(getPortfolioName(), name);
        waitForElementClickable(buttonUpdatePortfolio);
        buttonUpdatePortfolio.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage deleteUserPortfolio() {
        waitForElementClickable(buttonDeletePortfolio);
        buttonDeletePortfolio.click();
        MyLogger.info("Portfolio was deleted successfully.");
        return new PortfolioPage(driver);
    }
}
