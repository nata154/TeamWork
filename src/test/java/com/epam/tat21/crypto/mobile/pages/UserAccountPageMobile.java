package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserAccountPageMobile extends BasePageMobile {

    @FindBy(xpath = "//android.widget.TextView[@text='Log out']")
    private WebElement logOutButton;

    public UserAccountPageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MainCryptoComparePageMobile clickLogOutButton() {
        logOutButton.click();
        MyLogger.info("Log out button was clicked.");
        return new MainCryptoComparePageMobile(driver);
    }
}
