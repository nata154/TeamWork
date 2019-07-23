package com.epam.tat21.crypto.mobile.pages;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class MainCryptoComparePageMobile extends BasePageMobile {

    public MainCryptoComparePageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public UserAccountPageMobile clickUserAccountIcon() {
        List<MobileElement> userImage = driver.findElements(By.className("android.view.ViewGroup"));
        userImage.get(4).click();
        MyLogger.info("Click icon and enter user account.");
        return new UserAccountPageMobile(driver);
    }
}

