package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.User;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainCryptoComparePage extends HeaderPage {

    private final String BASE_URL = "https://www.cryptocompare.com/";

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
        return this;
    }

    public MainCryptoComparePage fillLoginForm(User user) {
        waitForElementVisible(emailField);
        emailField.sendKeys(user.getUserName());
        passwordField.sendKeys(user.getUserPassword());
        return this;
    }

    public MainCryptoComparePage clickLoginButton() {
        waitForElementVisible(loginButton);
        loginButton.click();
        return this;
    }

    public MainCryptoComparePage login(User user) {
        goToLoginForm();
        fillLoginForm(user).clickLoginButton();
        return this;
    }
}
