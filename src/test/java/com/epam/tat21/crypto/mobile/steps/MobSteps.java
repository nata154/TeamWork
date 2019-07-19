package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverFactory;
import com.epam.tat21.crypto.mobile.driver.MobileDriverForFarm;
import com.epam.tat21.crypto.mobile.driver.MobileLocalDriver;
import com.epam.tat21.crypto.mobile.pages.LoginPageMobile;
import com.epam.tat21.crypto.mobile.pages.MainCryptoComparePageMobile;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.service.UserCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobSteps {

    private AppiumDriver<MobileElement> driver;

    public MobileDriverFactory getMobileDriverFactory() {
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

    public void startDevice() {
        driver = getMobileDriverFactory().getDriver();
    }

    public void closeDevice() {
        getMobileDriverFactory().closeDriver();
    }

    //@Given("I login user")
    public MainCryptoComparePageMobile loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new LoginPageMobile(driver)
                .login(user);
    }

    public MainCryptoComparePageMobile clickUserAccount() {
        return new MainCryptoComparePageMobile(driver)
                .clickUserIcon();
    }
}
