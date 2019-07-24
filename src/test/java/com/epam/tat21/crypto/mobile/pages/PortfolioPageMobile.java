package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;

import java.util.List;

public class PortfolioPageMobile extends BasePageMobile {

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='\uF140']")
    private AndroidElement submenuArrow;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='ADD PORTFOLIO']")
    private AndroidElement addPortfolioButton;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Portfolio Name']")
    private AndroidElement portfolioNameField;

    //private static final String ADDING_COIN_IN_PORTFOLIO_XPATH = "//android.view.ViewGroup[@text='";
    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Name']")
    private AndroidElement freeSpace;

    @AndroidFindBy(xpath = "//android.view.ViewGroup[@text='BTC']")
    private AndroidElement selectAddingCoinInPortfolio;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add a description or leave this area empty']")
    private AndroidElement portfolioDescriptionField;

    @AndroidFindBy(xpath = "//android.widget.TextView[@text='Submit']")
    private AndroidElement buttonSubmit;

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
        // selectAddingCoinInPortfolio.click();
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
        //return  textViews.get(0).getAttribute("text");
    }
}
