package com.epam.tat21.crypto.mobile.mobiledriver;

import com.epam.tat21.crypto.utils.MyLogger;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

import static com.epam.tat21.crypto.service.TestDataReader.getMobileAppPackage;
import static com.epam.tat21.crypto.service.TestDataReader.getMobileLocalUrl;

public class MobileLocalDriver implements MobileDriverFactory {

    private DesiredCapabilities capabilities;
    private static AppiumDriver<MobileElement> appiumDriver;

    public MobileLocalDriver() {
        capabilities = MobCapabilitiesProvider.getCapabilities();
        capabilities.setCapability(MobileCapabilityType.APPIUM_VERSION, System.getProperty("appium"));
        capabilities.setCapability(MobileCapabilityType.PLATFORM_VERSION, "7.1");
        capabilities.setCapability(MobileCapabilityType.DEVICE_NAME, System.getProperty("device"));
    }

    @Override
    public AppiumDriver<MobileElement> getDriver() {
        if (appiumDriver == null) {
            try {
                appiumDriver = new AndroidDriver<>(new URL(getMobileLocalUrl()), capabilities);
            } catch (MalformedURLException e) {
                MyLogger.error(e.getMessage());
            }
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
