package com.epam.tat21.crypto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {

    private final String BASE_URL = "https://www.cryptocompare.com/";

    @FindBy(xpath = "//a[@href='/coins/list/' and @class='uib-dropdown-toggle']")
    private WebElement coinsTabLink;

    @FindBy(xpath = "//a[@href='/exchanges/' and @class='uib-dropdown-toggle']")
    private WebElement exchangesTabLink;

    @FindBy(xpath = "//a[@href='/mining/' and @class='uib-dropdown-toggle']")
    private WebElement miningTabLink;

    @FindBy(xpath = "//a[@href='/wallets/' and @class='uib-dropdown-toggle']")
    private WebElement walletsTabLink;

    @FindBy(xpath = "//a[@href='/news/list/latest/' and @class='uib-dropdown-toggle']")
    private WebElement newsTabLink;

    @FindBy(xpath = "//a[@href='/portfolio/' and @class='uib-dropdown-toggle']")
    private WebElement portfolioTabLink;

    @FindBy(xpath = "//a[@href='https://min-api.cryptocompare.com' and @class='uib-dropdown-toggle']")
    private WebElement apiTabLink;

    @FindBy(linkText = "Login")
    private WebElement logInLink;

    @FindBy (linkText = "My Portfolios")
    private WebElement myPortfoliosLink;


    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public HeaderPage goToLoginForm(){
        waitForElementVisible(logInLink);
        logInLink.click();
        return this;
    }

    public HeaderPage moveToCoinsTab(){
        waitForElementVisible(coinsTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(coinsTabLink).build().perform();
        return this;
    }

    public HeaderPage moveToExchangesTab(){
        waitForElementVisible(exchangesTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(exchangesTabLink).build().perform();
        return this;
    }

    public HeaderPage moveToPortfolioTab(){
        waitForElementVisible(portfolioTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(portfolioTabLink).build().perform();
        return this;
    }


}
