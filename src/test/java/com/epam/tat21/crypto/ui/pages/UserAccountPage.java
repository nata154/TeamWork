package com.epam.tat21.crypto.ui.pages;

import com.epam.tat21.crypto.ui.elements.menus.HeaderMenu;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAccountPage extends BasePage {

    private static final String BASE_URL = TestDataReader.getApplicationUrl() + "cryptopian/general";
    private HeaderMenu headerMenu;

    @FindBy(xpath = "//span[contains(text(), 'General')]")
    private WebElement tabGeneral;

    @FindBy(xpath = "//span[contains(text(), 'Security')]")
    private WebElement tabSecurity;

    @FindBy(xpath = "//span[contains(text(), 'Email Notifications')]")
    private WebElement tabEmailNotification;

    @FindBy(xpath = "//span[contains(text(), 'API Keys')]")
    private WebElement tabApiKeys;

    @FindBy(xpath = "//span[contains(text(), 'Billing')]")
    private WebElement tabBilling;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstNameLineInGeneralTab;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement surnameLineInGeneralTab;

    By genderLineInGeneralTab = By.xpath("//div[@class='ui-select']/select");

    @FindBy(xpath = "//button[@class='btn btn-success btn-lg']")
    private WebElement buttonSaveChangesInGeneralTab;

    @FindBy(xpath = "//div[@class='toast-notifications']//div[@ng-bind-html='notification.Message']")
    private WebElement popupWindowAfterSavingUpdates;

    @Override
    public BasePage openPage() {
        driver.navigate().to(BASE_URL);
        MyLogger.info("UserAccountPage was opened");
        return this;
    }

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public UserAccountPage clickTabGeneral() {
        waitForElementClickable(tabGeneral);
        tabGeneral.click();
        return this;
    }

    public UserAccountPage enterNewFirstNameAndSurname(String firstName, String surname) {
        waitForElementClickable(firstNameLineInGeneralTab);
        firstNameLineInGeneralTab.click();
        firstNameLineInGeneralTab.clear();
        firstNameLineInGeneralTab.sendKeys(firstName);
        waitForElementClickable(surnameLineInGeneralTab);
        surnameLineInGeneralTab.click();
        surnameLineInGeneralTab.clear();
        surnameLineInGeneralTab.sendKeys(surname);
        return this;
    }

    public UserAccountPage clickButtonSaveChanges() {
        waitForElementClickable(buttonSaveChangesInGeneralTab);
        buttonSaveChangesInGeneralTab.click();
        return this;
    }

    public String getInfoFromPopupWindow() {
        waitForElementVisible(popupWindowAfterSavingUpdates);
        return popupWindowAfterSavingUpdates.getText();
    }
}
