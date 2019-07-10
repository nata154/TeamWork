package com.epam.tat21.crypto.ui.pages;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.epam.tat21.crypto.ui.businessObjects.Coin;

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
    
    @FindBy(xpath = "//span[contains(text(), 'Update')]")
	private WebElement buttonUpdateCoin;
    
    @FindBy(xpath = "//span[contains(text(), 'Delete')]")
	private WebElement buttonDeleteCoin;

    public AddCoinForm(WebDriver driver) {
        super(driver);
    }

    public AddCoinForm scrollToNeededCoin() {
        waitForElementClickable(searchCoinField);
        searchCoinField.click();
        waitForElementClickable(coinsDropdown);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].scrollIntoView(true);", coinsDropdown);
        return this;
    }

    public AddCoinForm selectCoinInDropdown() {
        waitForElementClickable(firstCoinInDropdown);
        firstCoinInDropdown.click();
        return this;
    }

    public AddCoinForm inputCoinInSearchField(Coin coin) {
        waitForElementClickable(searchCoinField);
        searchCoinField.sendKeys(coin.getNameOfCoin());
        selectCoinInDropdown();
        return this;
    }

    public AddCoinForm inputAmountOfCoinInField(String amount) {
        waitForElementClickable(amountField);
        amountField.sendKeys(amount);
        return this;
    }

    public AddCoinForm inputbBuyPriceOfCoinInField(String price) {
        waitForElementClickable(buyPriceField);
        buyPriceField.sendKeys(price);
        return this;
    }

    public PortfolioPage addCoinInPortfolio(Coin coin, String amount, String price) {
        inputCoinInSearchField(coin)
                .inputAmountOfCoinInField(amount)
                .inputbBuyPriceOfCoinInField(price);
        waitForElementClickable(addPortfolioButton);
        addPortfolioButton.click();
        return new PortfolioPage(driver);
    }
    
    public PortfolioPage editAmountOfCoin(String amount) {
		waitForElementVisible(amountField);
		amountField.clear();
		amountField.sendKeys(amount);
		waitForElementClickable(buttonUpdateCoin);
		buttonUpdateCoin.click();
		return new PortfolioPage(driver);
	}
    
    public PortfolioPage deleteCoinFromPortfolio() {
		waitForElementClickable(buttonDeleteCoin);
		buttonDeleteCoin.click();
		return new PortfolioPage(driver);
	}
}
