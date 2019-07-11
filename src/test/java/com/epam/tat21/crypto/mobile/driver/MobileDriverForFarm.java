package com.epam.tat21.crypto.mobile.driver;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.epam.tat21.crypto.ui.service.TestDataReader.*;

public class MobileDriverForFarm implements MobileDriverFactory {

    private static AppiumDriver<MobileElement> appiumDriver = null;
    private DesiredCapabilities capabilities;

    public MobileDriverForFarm() {
        capabilities = MobCapabilitiesProvider.getCapabilities();
        capabilities.setCapability(MobileCapabilityType.UDID, getMobileDeviceUuid());
        capabilities.setCapability("platformVersion", "8.0.0");
    }

    @Override
    public AppiumDriver<MobileElement> getDriver() {
        if (appiumDriver == null)
            try {
                appiumDriver = new AppiumDriver<>(new URL(getMobileFarmUrl()), capabilities);
            } catch (MalformedURLException e) {
                MyLogger.error(e.getMessage());
            }
        appiumDriver.manage().timeouts().implicitlyWait(90, TimeUnit.SECONDS);
        return appiumDriver;
    }

    @Override
    public void closeDriver() {
        appiumDriver.closeApp();
        appiumDriver.removeApp(getMobileAppPackage());
        Optional.ofNullable(appiumDriver).ifPresent(RemoteWebDriver::quit);
    }
}
