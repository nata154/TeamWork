package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
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

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.HorizontalScrollView\").className(\"android.widget.TextView\")")
    private List<AndroidElement> currencies;

    @AndroidFindBy(xpath = "//android.widget.EditText[@text='Add a description or leave this area empty']")
    private AndroidElement portfolioDescriptionField;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Submit\")")
    private AndroidElement buttonSubmit;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
    private AndroidElement textViews;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Edit\")")
    private AndroidElement buttonEdit;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.EditText\").index(0)")
    private AndroidElement portfolioNameEdit;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"\uF1F8 Delete\")")
    private AndroidElement buttonDelete;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Delete Portfolio\")")
    private AndroidElement buttonConfirmDeletePortfolio;

    public PortfolioPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public PortfolioPageMobile inputNewPortfolioValues(String name, String currency, String description) {
        submenuArrow.click();
        addPortfolioButton.click();
        MyLogger.info("Add portfolio button was clicked");
        portfolioNameField.click();
        portfolioNameField.sendKeys(name);
        MyLogger.info("Portfolio got name - " + name);
        freeSpace.click();
        chooseCurrency(currency).click();
        portfolioDescriptionField.click();
        portfolioDescriptionField.sendKeys(description);
        freeSpace.click();
        return this;
    }

    private List<AndroidElement> getAvailableCurrencies() {
        return currencies;
    }

    private WebElement chooseCurrency(String currency) {
        return getAvailableCurrencies().stream().filter(item -> item.getText().equals(currency)).findAny().orElse(null);
    }

    public PortfolioPageMobile submitCreatingPortfolio() {
        buttonSubmit.click();
        MyLogger.info("Submit creating portfolio.");
        return this;
    }

    public String getCurrentPortfolioName() {
        return textViews.getText();
    }

    public PortfolioPageMobile clickButtonEdit() {
        buttonEdit.click();
        MyLogger.info("Click button edit portfolio.");
        return this;
    }

    public PortfolioPageMobile changeNameOfPortfolio(String changedName) {
        portfolioNameEdit.click();
        portfolioNameEdit.clear();
        portfolioNameEdit.sendKeys(changedName);
        MyLogger.info("Portfolio name was changed on - " + changedName);
        freeSpace.click();
        return this;
    }

    public PortfolioPageMobile submitEditingPortfolio() {
        buttonSubmit.click();
        MyLogger.info("Submit editing portfolio.");
        return this;
    }

    public PortfolioPageMobile clickButtonDelete() {
        buttonDelete.click();
        MyLogger.info("Function delete portfolio was clicked.");
        buttonConfirmDeletePortfolio.click();
        MyLogger.info("Deleting portfolio was confirmed.");
        return this;
    }

    public boolean isPortfolioWithSuchNameDeleted(String changedName) {
        submenuArrow.click();
        return isPortfolioDeleted(changedName);
    }

    private boolean isPortfolioDeleted(String changedName) {
        MyLogger.info("Trying to find deleted portfolio.");
        boolean portfolioIsDeleted = false;
        //@AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\")")
        //has really worse performance here. difference is 1,5 minutes
        List<MobileElement> allPortfolioList = driver.findElements(By.className("android.widget.TextView"));
        if (!getListOfPortfolios(allPortfolioList).contains(changedName)) {
            portfolioIsDeleted = true;
            MyLogger.info("Portfolio with name " + changedName + " absent in list of portfolio names.");
        }
        return portfolioIsDeleted;
    }

    private List<String> getListOfPortfolios(List<MobileElement> allPortfolioList) {
        List<String> allPortfolioNames = new ArrayList<String>();
        String nameOfPortfolio = null;
        for (int i = 0; i < allPortfolioList.size(); i++) {
            nameOfPortfolio = allPortfolioList.get(i).getAttribute("text");
            allPortfolioNames.add(nameOfPortfolio);
        }
        MyLogger.info("List of existing portfolio name was created");
        return allPortfolioNames;
    }
}
