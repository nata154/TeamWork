package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class LoginPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Log in\")")
    private AndroidElement loginFunction;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Your e-mail\")")
    private AndroidElement loginFieldName;

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Password\")")
    private AndroidElement loginFieldPassword;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.view.ViewGroup\").index(6)")
    private AndroidElement loginButton;

    @AndroidFindBy(uiAutomator = "new UiSelector().className(\"android.widget.TextView\").index(0)")
    private AndroidElement textViews;

    public LoginPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public LoginPageMobile skipPreview() {
        textViews.click();
        MyLogger.info("Preview skipped.");
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
        loginButton.click();
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
