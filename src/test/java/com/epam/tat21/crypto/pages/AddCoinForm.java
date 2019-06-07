package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class AddCoinForm extends HeaderPage {

    @FindBy(xpath = "//input[@type='search']")
    private WebElement searchCoinField;

    @FindBy(xpath = "//input[@name='amount']")
    private WebElement amountField;

    @FindBy(xpath = "//input[@name='buyPrice']")
    private WebElement buyPriceField;

    @FindBy(xpath = "//span[@class='ng-scope' and contains(text(), 'Add To Portfolio')]")
    private WebElement addPortfolioButton;

    @FindBy(className = "md-autocomplete-suggestions autocomplete-custom-template")
    private WebElement coinsDropdown;

    @FindBy(xpath = "//ul[@class='md-autocomplete-suggestions autocomplete-custom-template']/li[1]")
    private WebElement firstCoinInDropdown;

    public AddCoinForm(WebDriver driver) {
        super(driver);
    }

    public AddCoinForm scrollToNeededCoin() {
        waitForElementClicable(searchCoinField);
        searchCoinField.click();
        waitForElementClicable(coinsDropdown);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", coinsDropdown);
        return this;
    }

    public AddCoinForm selectCoinInDropdown() {
        waitForElementClicable(firstCoinInDropdown);
        firstCoinInDropdown.click();
        return this;
    }

    public AddCoinForm inputCoinInSearchField(Coin coin) {
        waitForElementClicable(searchCoinField);
        searchCoinField.sendKeys(coin.getNameOfCoin());
        selectCoinInDropdown();
        return this;
    }

    public AddCoinForm inputAmountOfCoinInField(String amount) {
        waitForElementClicable(amountField);
        amountField.sendKeys(amount);
        return this;
    }

    public AddCoinForm inputbBuyPriceOfCoinInField(String price) {
        waitForElementClicable(buyPriceField);
        buyPriceField.sendKeys(price);
        return this;
    }

    public AddCoinForm addCoinInPortfolio(Coin coin, String amount, String price) {
        inputCoinInSearchField(coin)
                .inputAmountOfCoinInField(amount)
                .inputbBuyPriceOfCoinInField(price);
        waitForElementClicable(addPortfolioButton);
        addPortfolioButton.click();
        return this;
    }
}