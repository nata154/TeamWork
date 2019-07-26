package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidElement;
import io.appium.java_client.pagefactory.AndroidFindBy;

public class UserAccountPageMobile extends BasePageMobile {

    @AndroidFindBy(uiAutomator = "new UiSelector().text(\"Log out\")")
    private AndroidElement logOutButton;

    public UserAccountPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MainCryptoComparePageMobile clickLogOutButton() {
        logOutButton.click();
        MyLogger.info("Log out button was clicked.");
        return new MainCryptoComparePageMobile(driver);
    }
}
