package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class PortfolioPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"\uF140\")")
    private AndroidElement submenuArrow;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"ADD PORTFOLIO\")")
    private AndroidElement addPortfolioButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Portfolio Name\")")
    private AndroidElement portfolioNameField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Currency \")")
    private AndroidElement freeSpace;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"BTC\")")
    private AndroidElement selectAddingCoinInPortfolio;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add a description or leave this area empty']")
    private AndroidElement portfolioDescriptionField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Submit\")")
    private AndroidElement buttonSubmit;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Edit\")")
    private AndroidElement buttonEdit;


    public PortfolioPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public PortfolioPageMobile inputNewPortfolioInfo(String name, String currency, String description) {
        submenuArrow.click();
        addPortfolioButton.click();
        MyLogger.info("Add portfolio button was clicked");
        portfolioNameField.click();
        portfolioNameField.sendKeys(name);
        MyLogger.info("Portfolio get name - " + name);
        freeSpace.click();
        //WebElement selectAddingCoinInPortfolio = driver.findElement(By.xpath(ADDING_COIN_IN_PORTFOLIO_XPATH + currency + "']"));
        selectAddingCoinInPortfolio.click();
        portfolioDescriptionField.click();
        portfolioDescriptionField.sendKeys(description);
        freeSpace.click();
        return this;
    }

    public PortfolioPageMobile submitCreatingPortfolio() {
        buttonSubmit.click();
        MyLogger.info("Click button create portfolio.");
        return this;
    }

    public String getCurrentPortfolioName() {
        List<MobileElement> textViews = driver.findElements(By.className("android.widget.TextView"));
        return textViews.get(0).getText();
    }

    public PortfolioPageMobile clickButtonEdit() {
        buttonEdit.click();
        return this;
    }

    public PortfolioPageMobile changeNameOfPortfolio(String changedName) {
        List<MobileElement> portfolioNameEdit = driver.findElements(By.className("android.widget.EditText"));
        portfolioNameEdit.get(0).click();
        portfolioNameEdit.get(0).clear();
        portfolioNameEdit.get(0).sendKeys(changedName);
        freeSpace.click();
        return this;
    }

    public PortfolioPageMobile submitEditingPortfolio() {
        buttonSubmit.click();
        MyLogger.info("Click button create portfolio.");
        return this;
    }
}
