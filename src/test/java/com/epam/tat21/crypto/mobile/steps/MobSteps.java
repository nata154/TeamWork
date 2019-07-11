package com.epam.tat21.crypto.mobile.steps;

import com.epam.tat21.crypto.mobile.driver.MobileDriverFactory;
import com.epam.tat21.crypto.mobile.driver.MobileDriverForFarm;
import com.epam.tat21.crypto.mobile.driver.MobileLocalDriver;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.MobileElement;
import org.openqa.selenium.By;

import java.util.List;

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

    public void previewApp() {
        List<MobileElement> textViews=driver.findElements(By.className("android.widget.TextView"));
        textViews.get(1).click();
    }
}
