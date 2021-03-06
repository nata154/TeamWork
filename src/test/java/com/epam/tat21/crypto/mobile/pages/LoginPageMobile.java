package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class LoginPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Log in\")")
    private AndroidElement loginFunction;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Your e-mail\")")
    private AndroidElement loginFieldName;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
    private AndroidElement loginFieldPassword;

    public LoginPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public LoginPageMobile skipPreview() {
        //login button in order to skip preview doesn't have special text
        new WebDriverWait(driver, 5).
                until(ExpectedConditions.numberOfElementsToBeMoreThan(By.className("android.widget.TextView"), 1));
        List<MobileElement> textViews = driver.findElements(By.className("android.widget.TextView"));
        textViews.get(1).click();
        return this;
    }

    public LoginPageMobile clickLoginChoice() {
        loginFunction.click();
        MyLogger.info("Function log in was chosen.");
        return this;
    }

    public LoginPageMobile fillLoginForm(User user) {
        loginFieldName.click();
        loginFieldName.sendKeys(user.getUserName());
        loginFieldPassword.click();
        loginFieldPassword.sendKeys(user.getUserPassword());
        MyLogger.info("User name and password were inputed.");
        return this;
    }

    public LoginPageMobile clickLoginButton() {
        //login button doesn't have special text
        List<MobileElement> loginButton = driver.findElements(By.className("android.view.ViewGroup"));
        loginButton.get(6).click();
        MyLogger.info("LogIn button was clicked.");
        return this;
    }

    public MainCryptoComparePageMobile login(User user) {
        skipPreview();
        clickLoginChoice();
        fillLoginForm(user);
        clickLoginButton();
        return new MainCryptoComparePageMobile(driver);
    }

    public boolean isFieldPasswordVisible() {
        boolean fieldPassword = false;
        if (loginFieldPassword.isDisplayed()) {
            fieldPassword = true;
            MyLogger.info("Field 'Password' is visible while checking Log Out");
        }
        return fieldPassword;
    }
}
