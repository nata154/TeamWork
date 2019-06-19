package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.PortfolioItem;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import com.epam.tat21.crypto.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class PortfolioPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    private final String PORTFOLIO_ITEM_LINK_LOCATOR = "//md-tab-item";

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
        waitForElementClickable(addCoinToPortfolioButton);
        addCoinToPortfolioButton.click();
        MyLogger.info("AddCoinForm was appeared");
        return new AddCoinForm(driver);
    }

    public AddPortfolioForm addPortfolioForm() {
        waitForElementClickable(buttonAddPortfolio);
        buttonAddPortfolio.click();
        MyLogger.info("AddPortfolioForm was appeared");
        return new AddPortfolioForm(driver);
    }


    public List<WebElement> getPortfolioItemLinkList() {

        WaitConditions.waitForVisibilityOfAllElementsByXpath(driver, PORTFOLIO_ITEM_LINK_LOCATOR, 5);

        return driver.findElements(By.xpath(PORTFOLIO_ITEM_LINK_LOCATOR));
    }

    public PortfolioItem getPortfolioItem(int index) {
        getPortfolioItemLinkList().get(index).click();
        return new PortfolioItem(driver);
    }


}
