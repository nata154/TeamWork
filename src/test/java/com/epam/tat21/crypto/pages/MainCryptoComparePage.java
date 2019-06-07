package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainCryptoComparePage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl();

    @FindBy(xpath = "//input[@name='email']")
    private WebElement emailField;

    @FindBy(xpath = "//input[@name='password']")
    private WebElement passwordField;

    @FindBy(xpath = "//button[@value='Login']")
    private WebElement loginButton;

    public MainCryptoComparePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public MainCryptoComparePage openPage() {
        driver.navigate().to(BASE_URL);


        MyLogger.info("MainCryptoComparePage was opened");
        return this;
    }

    public MainCryptoComparePage fillLoginForm(User user) {
        waitForElementVisible(emailField);
        emailField.sendKeys(user.getUserName());
        MyLogger.info("Email on login form was filled");
        passwordField.sendKeys(user.getUserPassword());
        MyLogger.info("Password  on login form was filled");
        return this;
    }

    public MainCryptoComparePage clickLoginButton() {
        waitForElementVisible(loginButton);
        loginButton.click();
        MyLogger.info("Login button was pressed");
        return this;
    }

    public MainCryptoComparePage login(User user) {
        goToLoginForm();
        fillLoginForm(user).clickLoginButton();
        return this;
    }


}
