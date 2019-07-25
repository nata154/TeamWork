package com.epam.tat21.crypto.mobile.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobileDriverManager {

    private static AppiumDriver<MobileElement> driver;

    private MobileDriverManager() {
    }

    public static  MobileDriverFactory getMobileDriverFactory() {
        if (driver == null) {
            switch (System.getProperty("mobile")) {
                case "local":
                    return new MobileLocalDriver();
                case "farm":
                    return new MobileDriverForFarm();
                default:
                    return new MobileLocalDriver();
            }
        }
        return new MobileLocalDriver();
    }
}
