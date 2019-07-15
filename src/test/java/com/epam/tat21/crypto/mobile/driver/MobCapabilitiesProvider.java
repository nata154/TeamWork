package com.epam.tat21.crypto.mobile.driver;

import io.appium.java_client.remote.MobileCapabilityType;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.io.File;

import static com.epam.tat21.crypto.ui.service.TestDataReader.*;

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

        capabilities.setCapability("deviceName", getMobileDeviceName());

//         capabilities = new DesiredCapabilities().android();
//        capabilities.setCapability("no",true);
//        capabilities.setCapability("newCommandTimeout", 100000);
//        capabilities.setCapability("noReset", true);
//        //capabilities.setCapability(CapabilityType.BROWSER_NAME, "");
//        capabilities.setCapability(CapabilityType.VERSION, "4.4.2");
//        capabilities.setCapability("deviceName", "Galaxy nexus");
//        capabilities.setCapability("app", application.getAbsolutePath());
//        capabilities.setCapability("automationName", "selendroid");
//        capabilities.setCapability("noRest", true);
//        driver = new AndroidDriver<MobileElement>(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);

//        mobile.app = com.cryptocompare.mainapp.apk
//        mobile.app.package=com.cryptocompare.mainapp
//        mobile.local.url = http://0.0.0.0:4723/wd/hub
//        mobile.farm.url = http://EPM-TSTF:1fa5e1be-21b1-4997-b058-73bb10559cd2@mobilecloud.epam.com:8080/wd/hub
//#Samsung Galaxy S9 Plus
//        serial.number.device = 225045 a40a017ece
//        device.name = SAMSUNG SM - G965F
//        getMobileDeviceName
        return capabilities;
    }
}
