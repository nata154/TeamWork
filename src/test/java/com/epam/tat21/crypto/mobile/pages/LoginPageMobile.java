package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.businessObjects.User;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;


public class LoginPageMobile extends BasePageMobile{

    public LoginPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public LoginPageMobile skipPreview() {
        List<MobileElement> textViews = driver.findElements(By.className("android.widget.TextView"));
        textViews.get(1).click();
        return this;
    }

    public LoginPageMobile clickLoginFunction() {
        List<MobileElement> loginFunction = driver.findElements(By.className("android.view.ViewGroup"));
        loginFunction.get(1).click();
        loginFunction.get(7).click();
        return this;
    }

    public LoginPageMobile fillLoginForm(User user) {
        List<MobileElement> loginFields = driver.findElements(By.className("android.widget.EditText"));
        loginFields.get(0).click();
        loginFields.get(0).sendKeys(user.getUserName());
        loginFields.get(1).click();
        loginFields.get(1).sendKeys(user.getUserPassword());
        return this;
    }

    public LoginPageMobile clickLoginButton() {
        List<MobileElement> loginButton = driver.findElements(By.className("android.view.ViewGroup"));
        loginButton.get(6).click();
        return this;
    }

    public MainCryptoComparePageMobile login(User user) {
        skipPreview();
        clickLoginFunction();
        fillLoginForm(user);
        clickLoginButton();
        return new MainCryptoComparePageMobile(driver);
    }
}
