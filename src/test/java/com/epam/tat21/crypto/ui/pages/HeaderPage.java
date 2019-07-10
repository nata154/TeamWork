package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

public class HeaderPage extends BasePage {

    private final String BASE_URL = TestDataReader.getApplicationUrl();

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

    @FindBy(linkText = "My Portfolios")
    private WebElement myPortfoliosLinkInPorfolioTab;

    @FindBy(xpath = "//li[@class='dropdown navbar-profile']/a")
    private WebElement userLineInHeader;

    public HeaderPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HeaderPage openPage() {
        driver.get(BASE_URL);
        return this;
    }

    public HeaderPage goToLoginForm() {
        waitForElementVisible(logInLink);
        logInLink.click();
        MyLogger.info("Go to Login form");
        return this;
    }

    public HeaderPage moveToCoinsTab() {
        waitForElementVisible(coinsTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(coinsTabLink).build().perform();
        return this;
    }

    public HeaderPage moveToExchangesTab() {
        waitForElementVisible(exchangesTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(exchangesTabLink).build().perform();
        return this;
    }

    public HeaderPage moveToPortfolioTab() {
        waitForElementVisible(portfolioTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(portfolioTabLink).build().perform();
        return this;
    }

    public NewsPage moveToNewsTab() {
        waitForElementVisible(newsTabLink);
        Actions action = new Actions(driver);
        action.moveToElement(newsTabLink).build().perform();
        return new NewsPage(driver);
    }

    public NewsPage goToNewsTab() {
        waitForElementClickable(newsTabLink);
        newsTabLink.click();
        return new NewsPage(driver);
    }

    public CoinsPage goToCoinsPage() {
        waitForElementClickable(coinsTabLink);
        coinsTabLink.click();
        return new CoinsPage(driver);
    }

    public ExchangesPage goToExchangesPage() {
        waitForElementClickable(exchangesTabLink);
        exchangesTabLink.click();
        return new ExchangesPage(driver);
    }

    public PortfolioPage goToPortfolioPage() {
        waitForElementClickable(portfolioTabLink);
        portfolioTabLink.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage goToMyPortfolioFromPortfolioTab() {
        moveToPortfolioTab();
        waitForElementClickable(myPortfoliosLinkInPorfolioTab);
        myPortfoliosLinkInPorfolioTab.click();
        return new PortfolioPage(driver);
    }

    public UserDropdownMenuInHeader getDropdownUserMenuInHeader() {
        waitForElementClickable(userLineInHeader);
        userLineInHeader.click();
        return new UserDropdownMenuInHeader(driver);
    }


}
