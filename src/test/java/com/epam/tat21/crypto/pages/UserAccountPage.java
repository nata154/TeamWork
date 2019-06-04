package com.epam.tat21.crypto.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAccountPage extends HeaderPage {

    @FindBy(xpath = "//span[contains(text(), 'General')]")
    private WebElement tabGeneralInUserAccount;

    @FindBy(xpath = "//span[contains(text(), 'Security')]")
    private WebElement tabSecurityInUserAccount;

    @FindBy(xpath = "//span[contains(text(), 'Email Notifications')]")
    private WebElement tabEmailNotificationInUserAccount;

    @FindBy(xpath = "//span[contains(text(), 'API Keys')]")
    private WebElement tabApiKeysInUserAccount;

    @FindBy(xpath = "//span[contains(text(), 'Billing')]")
    private WebElement tabBillingInUserAccount;

    @FindBy(xpath = "//input[@name='first_name']")
    private WebElement firstNameLineInGeneralTabInUserAccount;

    @FindBy(xpath = "//input[@name='last_name']")
    private WebElement surnameLineInGeneralTabInUserAccount;

    By genderLineInGeneralTabInUserAccount = By.xpath("//div[@class='ui-select']/select");

    @FindBy(xpath = "//button[@class='btn btn-success btn-lg']")
    private WebElement buttonSaveChangesInGeneralTabInUserAccount;

    @FindBy(xpath = "//div[@class='toast-notifications']//div[@ng-bind-html='notification.Message']")
    private WebElement popupWindowAfterSavingUpdatedInUserAccount;

    public UserAccountPage(WebDriver driver) {
        super(driver);
    }

    public UserAccountPage clickTabGeneralInUserAccount() {
        waitForElementClicable(tabGeneralInUserAccount);
        tabGeneralInUserAccount.click();
        return this;
    }

    public UserAccountPage enterNewFirstNameAndSurnameInUserAccount(String firstName, String surname) {
        waitForElementClicable(firstNameLineInGeneralTabInUserAccount);
        firstNameLineInGeneralTabInUserAccount.click();
        firstNameLineInGeneralTabInUserAccount.clear();
        firstNameLineInGeneralTabInUserAccount.sendKeys(firstName);
        waitForElementClicable(surnameLineInGeneralTabInUserAccount);
        surnameLineInGeneralTabInUserAccount.click();
        surnameLineInGeneralTabInUserAccount.clear();
        surnameLineInGeneralTabInUserAccount.sendKeys(surname);
        return this;
    }

    public UserAccountPage clickButtonSaveChangesInUserAccount() {
        waitForElementClicable(buttonSaveChangesInGeneralTabInUserAccount);
        buttonSaveChangesInGeneralTabInUserAccount.click();
        return this;
    }

    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        waitForElementVisible(popupWindowAfterSavingUpdatedInUserAccount);
        String textOnPopupWindowAfterSavingUpdated = popupWindowAfterSavingUpdatedInUserAccount.getText();
        return textOnPopupWindowAfterSavingUpdated;
    }
}
