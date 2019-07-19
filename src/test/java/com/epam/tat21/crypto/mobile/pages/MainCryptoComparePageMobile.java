package com.epam.tat21.crypto.mobile.pages;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

public class MainCryptoComparePageMobile extends BasePageMobile {

    public MainCryptoComparePageMobile(AppiumDriver<MobileElement> driver) {
        super(driver);
    }

    public MainCryptoComparePageMobile clickUserIcon() {
        List<MobileElement> userImage = driver.findElements(By.className("android.view.ViewGroup"));
        userImage.get(0).click();
        return this;
    }
}

