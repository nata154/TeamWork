package com.epam.tat21.crypto.ui.elements.menus;

import com.epam.tat21.crypto.ui.pages.*;
import com.epam.tat21.crypto.ui.service.WebDriverAware;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import ru.yandex.qatools.htmlelements.element.HtmlElement;


@FindBy(xpath = "//div[contains(@class, 'navbar-main')]")
public class HeaderMenu extends HtmlElement implements WebDriverAware {

    private WebDriver driver;

    @FindBy(xpath = "//a[@href='/coins/list/' and @class='uib-dropdown-toggle']")
    private WebElement coinsTabLink;

    @FindBy(xpath = "//a[@href='/exchanges/' and @class='uib-dropdown-toggle']")
    private WebElement exchangesTabLink;

    @FindBy(xpath = "//a[@href='/news/list/latest/' and @class='uib-dropdown-toggle']")
    private WebElement newsTabLink;

    @FindBy(xpath = "//a[@href='/portfolio/' and @class='uib-dropdown-toggle']")
    private WebElement portfolioTabLink;

    @FindBy(linkText = "Login")
    private WebElement logInLink;

    @FindBy(linkText = "My Portfolios")
    private WebElement myPortfoliosLinkInPorfolioTab;

    @FindBy(xpath = "//li[@class='dropdown navbar-profile']/a")
    private WebElement userLineInHeader;

    @FindBy(xpath = "//ul[@class='nav navbar-nav navbar-right ng-scope']//ul[@class='uib-dropdown-menu dropdown-menu']")
    private UnorderedListDropdown userMenuDropdown;

    public void goToLoginForm() {
        WaitConditions.waitForClickableOfElement(logInLink, driver);
        logInLink.click();
        MyLogger.info("Go to Login form");
    }

    public void moveToCoinsTab() {
        WaitConditions.waitForClickableOfElement(coinsTabLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(coinsTabLink).build().perform();
    }

    public void moveToExchangesTab() {
        WaitConditions.waitForClickableOfElement(exchangesTabLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(exchangesTabLink).build().perform();
    }

    public void moveToPortfolioTab() {
        WaitConditions.waitForClickableOfElement(portfolioTabLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(portfolioTabLink).build().perform();
    }

    public void moveToNewsTab() {
        WaitConditions.waitForClickableOfElement(newsTabLink, driver);
        Actions action = new Actions(driver);
        action.moveToElement(newsTabLink).build().perform();
    }

    public NewsPage goToNewsPage() {
        WaitConditions.waitForClickableOfElement(newsTabLink, driver);
        newsTabLink.click();
        return new NewsPage(driver);
    }

    public CoinsPage goToCoinsPage() {
        WaitConditions.waitForClickableOfElement(coinsTabLink, driver);
        coinsTabLink.click();
        return new CoinsPage(driver);
    }

    public ExchangesPage goToExchangesPage() {
        WaitConditions.waitForClickableOfElement(exchangesTabLink, driver);
        exchangesTabLink.click();
        return new ExchangesPage(driver);
    }

    public PortfolioPage goToPortfolioPage() {
        WaitConditions.waitForClickableOfElement(portfolioTabLink, driver);
        portfolioTabLink.click();
        return new PortfolioPage(driver);
    }

    public PortfolioPage goToMyPortfolioFromPortfolioTab() {
        moveToPortfolioTab();
        WaitConditions.waitForClickableOfElement(myPortfoliosLinkInPorfolioTab, driver);
        myPortfoliosLinkInPorfolioTab.click();
        return new PortfolioPage(driver);
    }

    public void getUserMenuDropdown() {
        WaitConditions.waitForClickableOfElement(userLineInHeader, driver);
        userLineInHeader.click();
    }

    public UserAccountPage clickAccountLineInDropdown() {
        getUserMenuDropdown();
        WebElement accountLine = userMenuDropdown.chooseItemByVisibleText("Account");
        WaitConditions.waitForClickableOfElement(accountLine, driver);
        accountLine.click();
        return new UserAccountPage(driver);
    }

    public void clickLogInLink() {
        WaitConditions.waitForClickableOfElement(logInLink, driver);
        logInLink.click();
    }

    @Override
    public void setWebDriver(WebDriver driver) {
        this.driver = driver;
    }
}
