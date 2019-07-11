package com.epam.tat21.crypto.mobile.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public interface MobileDriverFactory {

    AppiumDriver<MobileElement> getDriver();

    void closeDriver();
}
