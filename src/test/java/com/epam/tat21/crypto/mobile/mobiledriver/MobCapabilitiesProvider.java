package com.epam.tat21.crypto.mobile.mobiledriver;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.epam.tat21.crypto.service.TestDataReader.getMobileApp;
import static com.epam.tat21.crypto.service.TestDataReader.getMobileAppPackage;

public class MobCapabilitiesProvider {

    private static DesiredCapabilities capabilities;

    private MobCapabilitiesProvider() {
    }

    public static DesiredCapabilities getCapabilities() {
        capabilities = new DesiredCapabilities();
        File classpathRoot = new File(System.getProperty("user.dir"));
        File appDir = new File(classpathRoot, "/app");
        File app = new File(appDir, getMobileApp());
        capabilities.setCapability(MobileCapabilityType.PLATFORM_NAME, "Android");
        capabilities.setCapability(MobileCapabilityType.APP, app.getAbsolutePath());
        capabilities.setCapability("appPackage", getMobileAppPackage());
        capabilities.setCapability("app-activity", ".MainActivity");
        capabilities.setCapability(MobileCapabilityType.BROWSER_NAME, "");
        return capabilities;
    }
}
