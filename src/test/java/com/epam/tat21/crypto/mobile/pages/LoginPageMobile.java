package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;


public class LoginPageMobile extends BasePageMobile{

    @FindBy(xpath = "//android.widget.TextView[@text='Log in']")
    private WebElement loginFunction;

    @FindBy(xpath = "//android.widget.EditText[@text='Your e-mail']")
    private WebElement loginFieldName;

    @FindBy(xpath = "//android.widget.EditText[@text='Password']")
    private WebElement loginFieldPassword;

    public LoginPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public LoginPageMobile skipPreview() {
        List<MobileElement> textViews = driver.findElements(By.className("android.widget.TextView"));
        textViews.get(1).click();
        return this;
    }

    public LoginPageMobile clickLoginChoice() {
        loginFunction.click();
        return this;
    }

    public LoginPageMobile fillLoginForm(User user) {
        loginFieldName.click();
        loginFieldName.sendKeys(user.getUserName());
        loginFieldPassword.click();
        loginFieldPassword.sendKeys(user.getUserPassword());
        return this;
    }

    public LoginPageMobile clickLoginButton() {
        //login button doesn't have special text
        List<MobileElement> loginButton = driver.findElements(By.className("android.view.ViewGroup"));
        loginButton.get(6).click();
        return this;
    }

    public MainCryptoComparePageMobile login(User user) {
        skipPreview();
        clickLoginChoice();
        fillLoginForm(user);
        clickLoginButton();
        return new MainCryptoComparePageMobile(driver);
    }
}
