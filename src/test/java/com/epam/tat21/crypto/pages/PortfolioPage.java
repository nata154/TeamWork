package com.epam.tat21.crypto.pages;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.epam.tat21.crypto.bo.PortfolioItem;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import com.epam.tat21.crypto.utils.WaitConditions;


public class PortfolioPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    private final String PORTFOLIO_ITEM_LINK_LOCATOR = "//md-tab-item";

    @FindBy(xpath = "//button[@ng-click='addPortfolioDialog()']")
    private WebElement buttonAddPortfolio;
    
    @FindBy(xpath = "(//md-tab-content[contains(@class,'active')]//span[contains(text(), 'Coin')])[1]")
    private WebElement addCoinToPortfolioButton;

    @FindBy(xpath = "//div[@class='list-table list-portfolio padding-5']")
    private WebElement tableOfCoinsInPortfolio;
    
    @FindBy(xpath = "//md-tab-content[contains(@class,'active')]//button[@ng-click='editPortfolioDialog()']")
    private WebElement buttonEditOrDelete;
    
    @FindBy(xpath = "//md-tab-content[contains(@class,'active')]//button[@ng-click='editPortfolioMemberDialog(portfolioMember)' and @uib-tooltip='Edit or delete']")
    private WebElement buttonEditOrDeleteCoin;
    
    @FindBy(xpath = "//*[contains(text(), 'Are you sure you want to delete')]")
    private WebElement  consentForm;

    @FindBy(xpath = "//*[contains(text(), 'Are you sure you want to remove')]")
    private WebElement  confirmCoinDeletingForm;
    
    @FindBy(xpath = "//span[contains(text(), 'Yes, delete portfolio')]")
    private WebElement buttonYesDeletePortfolio;
    
    @FindBy(xpath = "//span[contains(text(), 'Yes, remove coin')]")
    private WebElement buttonYesRemoveCoin;
    
    @FindBy(xpath = "//div[contains(text(), 'Your portfolio has been successfully deleted!')]")
    private WebElement popupSuccessfulDeleting;
    
    @FindBy(xpath = "//div[contains(text(), 'Your new coin has been successfully added!')]")
    private WebElement popupSuccessfulAdding;
    
    @FindBy(xpath = "//div[contains(text(), 'Your portfolio member has been successfully updated!')]")
    private WebElement popupSuccessfulCoinChanging;
    
    @FindBy(xpath = "//div[contains(text(), 'Your coin has been successfully removed!')]")
    private WebElement popupSuccessfulCoinDeleting;

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
    	waitForElementVisible(addCoinToPortfolioButton);
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
    
    public WebElement getElementPortfolio(String name) {
    	String xpathForPortfolio = "//span[contains(text(), '" + name + "')]";
    	WebElement portfolio = (new WebDriverWait(driver, 10))
    			   .until(ExpectedConditions.elementToBeClickable(By.xpath(xpathForPortfolio)));
	    return portfolio;
	}
    
    public AddPortfolioForm getEditPortfolioForm() {
    	waitForElementClickable(buttonEditOrDelete);
	    buttonEditOrDelete.click();
        MyLogger.info("EditPortfolioForm was appeared");
    	return new AddPortfolioForm(driver);
	}
    
    public AddCoinForm getEditCoinForm() {
    	//waitForElementVisible(buttonEditOrDeleteCoin);
        waitForElementClickable(buttonEditOrDeleteCoin);
        buttonEditOrDeleteCoin.click();
        MyLogger.info("EditCoinForm was appeared");
        return new AddCoinForm(driver);
	}
    
    public PortfolioPage confirmDeletion() {
		waitForElementVisible(consentForm);
		waitForElementClickable(buttonYesDeletePortfolio);
		buttonYesDeletePortfolio.click();
		return this;
	}

    public boolean isPortfolioDelete() {
    	waitForElementVisible(popupSuccessfulDeleting);
		return popupSuccessfulDeleting.isDisplayed();
	}
    
    public boolean isCoinAdded() {
    	waitForElementVisible(popupSuccessfulAdding);
		return popupSuccessfulAdding.isDisplayed();
	}
    
    public boolean isAmountOfCoinChanged() {
		waitForElementVisible(popupSuccessfulCoinChanging);
		return popupSuccessfulCoinChanging.isDisplayed();
	}
    
    public PortfolioPage confirmCoinDeletion() {
		waitForElementVisible(confirmCoinDeletingForm);
		waitForElementClickable(buttonYesRemoveCoin);
		buttonYesRemoveCoin.click();
		return this;
	}
    
    public boolean isCoinDelete() {
    	waitForElementVisible(popupSuccessfulCoinDeleting);
		return popupSuccessfulCoinDeleting.isDisplayed();
	}  
}
