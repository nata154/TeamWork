package com.epam.tat21.crypto.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class MainCryptoComparePage extends BasePage {

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
    public BasePage openPage() {
        driver.navigate().to(BASE_URL);
        return this;
    }

    public MainCryptoComparePage fillLoginForm(String email, String password) {
        waitForElementVisible(emailField);
        emailField.sendKeys(email);
        passwordField.sendKeys(password);
        return this;
    }

    public MainCryptoComparePage clickLoginButton() {
        loginButton.click();
        return this;
    }
}
