package com.epam.tat21.crypto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAccountPage extends HeaderPage {

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

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public UserAccountPage clickTabGeneral() {
        waitForElementClicable(tabGeneral);
        tabGeneral.click();
        return this;
    }

    public UserAccountPage enterNewFirstNameAndSurname(String firstName, String surname) {
        waitForElementClicable(firstNameLineInGeneralTab);
        firstNameLineInGeneralTab.click();
        firstNameLineInGeneralTab.clear();
        firstNameLineInGeneralTab.sendKeys(firstName);
        waitForElementClicable(surnameLineInGeneralTab);
        surnameLineInGeneralTab.click();
        surnameLineInGeneralTab.clear();
        surnameLineInGeneralTab.sendKeys(surname);
        return this;
    }

    public UserAccountPage clickButtonSaveChanges() {
        waitForElementClicable(buttonSaveChangesInGeneralTab);
        buttonSaveChangesInGeneralTab.click();
        return this;
    }

    public String getInfoFromPopupWindow() {
        waitForElementVisible(popupWindowAfterSavingUpdates);
        String textOnPopupWindowAfterSavingUpdated = popupWindowAfterSavingUpdates.getText();
        return textOnPopupWindowAfterSavingUpdated;
    }
}
