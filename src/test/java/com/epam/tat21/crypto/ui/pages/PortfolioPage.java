package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.PortfolioItem;
import com.epam.tat21.crypto.ui.elements.buttons.BaseButton;
import com.epam.tat21.crypto.ui.elements.forms.AddCoinForm;
import com.epam.tat21.crypto.ui.elements.forms.AddPortfolioForm;
import com.epam.tat21.crypto.ui.elements.forms.EditCoinForm;
import com.epam.tat21.crypto.ui.elements.forms.EditPortfolioForm;
import com.epam.tat21.crypto.ui.elements.toolbars.PortfolioToolbar;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.epam.tat21.crypto.ui.utils.WaitConditions;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class PortfolioPage extends HeaderPage {

    private static final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";
    private static final String PORTFOLIO_ITEM_LINK_LOCATOR = "//md-tab-item";
    private static final String POPUP_PORTFOLIO_CREATED = "//div[@class='toast-body ng-binding']";
    private static final String DIALOG_CONTAINER_LOCATOR = "//div[@class='md-dialog-container ng-scope']";
    private AddPortfolioForm addPortfolioForm;
    private EditPortfolioForm editPortfolioForm;
    private PortfolioToolbar portfolioToolbar;
    private AddCoinForm addCoinForm;
    private EditCoinForm editCoinForm;

    @FindBy(xpath = "//button[@ng-click='addPortfolioDialog()']")
    private BaseButton addPortfolioButton;

    @FindBy(xpath = "//md-tab-content[contains(@class,'active')]//button[@ng-click='editPortfolioMemberDialog(portfolioMember)' and @uib-tooltip='Edit or delete']")
    private BaseButton buttonEditOrDeleteCoin;

    @FindBy(xpath = "//*[contains(text(), 'Are you sure you want to delete')]")
    private WebElement consentForm;

    @FindBy(xpath = "//*[contains(text(), 'Are you sure you want to remove')]")
    private WebElement confirmCoinDeletingForm;

    @FindBy(xpath = "//span[contains(text(), 'Yes, delete portfolio')]")
    private BaseButton buttonYesDeletePortfolio;

    @FindBy(xpath = "//span[contains(text(), 'Yes, remove coin')]")
    private BaseButton buttonYesRemoveCoin;

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

    public PortfolioPage getAddCoinForm() {
        if (driver.findElements(By.xpath(POPUP_PORTFOLIO_CREATED)).size() == 0) {
            WaitConditions.waitForVisibilityOfAllElementsByXpath(driver, POPUP_PORTFOLIO_CREATED, 5);
        }
        portfolioToolbar.clickAddCoinButton();
        MyLogger.info("AddCoinForm was appeared");
        return this;
    }

    public PortfolioPage getAddPortfolioForm() {
        waitForElementClickable(addPortfolioButton);
        addPortfolioButton.click();
        MyLogger.info("AddPortfolioForm was appeared");
        return this;
    }

    public List<WebElement> getPortfolioItemLinkList() {
        WaitConditions.waitForVisibilityOfAllElementsByXpath(driver, PORTFOLIO_ITEM_LINK_LOCATOR, 5);
        return driver.findElements(By.xpath(PORTFOLIO_ITEM_LINK_LOCATOR));
    }

    public PortfolioItem getPortfolioItemByIndex(int index) {
        getPortfolioItemLinkList().get(index).click();
        return new PortfolioItem(driver);
    }

    public WebElement getPortfolioItemByName(String name) {
        String xpathForPortfolio = "//span[contains(text(), '" + name + "')]";
        return getPortfolioItemLinkList().stream().findFirst()
                .filter(portfolioLink -> portfolioLink.findElements(By.xpath(xpathForPortfolio)).size() != 0).get();

    }

    public PortfolioPage getEditPortfolioForm() {
        WaitConditions.waitForInvisibilityOfAllElementsByXpath(driver, DIALOG_CONTAINER_LOCATOR, 5);
        portfolioToolbar.clickEditPortfolioButton();
        MyLogger.info("EditPortfolioForm was appeared");
        return this;
    }

    public PortfolioPage getEditCoinForm() {
        WaitConditions.waitForInvisibilityOfAllElementsByXpath(driver, DIALOG_CONTAINER_LOCATOR, 5);
        waitForElementClickable(buttonEditOrDeleteCoin);
        buttonEditOrDeleteCoin.click();
        MyLogger.info("EditCoinForm was appeared");
        return this;
    }

    public PortfolioPage confirmDeletion() {
        waitForElementVisible(consentForm);
        waitForElementClickable(buttonYesDeletePortfolio);
        buttonYesDeletePortfolio.click();
        waitForElementVisible(popupSuccessfulDeleting);
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

    public PortfolioPage createNewPortfolio(String name, String currency, String description) {
        addPortfolioForm.inputPortfolioName(name);
        addPortfolioForm.selectCurrency(currency);
        addPortfolioForm.inputDescription(description);
        addPortfolioForm.clickCreateButton();
        return this;
    }

    public PortfolioPage editUserPortfolio(String name) {
        editPortfolioForm.changePortfolioName(name);
        editPortfolioForm.clickUpdatePortfolioButton();
        return this;
    }

    public PortfolioPage deleteUserPortfolio() {
        editPortfolioForm.clickDeletePortfolioButton();
        return this;
    }

    public PortfolioPage addCoinInPortfolio(Coin coin, String amount, String price) {
        addCoinForm.inputCoinInSearchField(coin);
        addCoinForm.inputAmountOfCoinInField(amount);
        addCoinForm.inputBuyPriceOfCoinInField(price);
        addCoinForm.clickAddToPortfolioButton();
        return this;
    }

    public PortfolioPage editAmountOfCoin(String amount) {
        editCoinForm.inputAmountOfCoinInField(amount);
        editCoinForm.clickUpdateCoinButton();
        return this;
    }

    public PortfolioPage deleteCoinFromPortfolio() {
        editCoinForm.clickDeleteCoinButton();
        return this;
    }

}
