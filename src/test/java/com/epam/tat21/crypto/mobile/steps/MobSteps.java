package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverManager;
import com.epam.tat21.crypto.mobile.pages.LoginPageMobile;
import com.epam.tat21.crypto.mobile.pages.MainCryptoComparePageMobile;
import com.epam.tat21.crypto.ui.businessObjects.User;
import com.epam.tat21.crypto.ui.service.UserCreator;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;

public class MobSteps {

    private AppiumDriver<MobileElement> driver;

    public MobSteps() {
        this.driver = MobileDriverManager.getMobileDriverFactory().getDriver();
    }

    public void closeDevice() {
        MobileDriverManager.getMobileDriverFactory().closeDriver();
    }

    //@Given("I login user")
    public MainCryptoComparePageMobile loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new LoginPageMobile(driver)
                .login(user);
    }

    public MainCryptoComparePageMobile clickUserAccountAndLogOut() {
        return new MainCryptoComparePageMobile(driver)
                .clickUserAccountIcon()
                .clickLogOutButton();
    }

    public boolean checkLogout() {
        return new LoginPageMobile(driver).isFieldPasswordVisible();
    }

}
