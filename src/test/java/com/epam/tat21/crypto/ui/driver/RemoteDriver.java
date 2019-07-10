package com.epam.tat21.crypto.ui.driver;

import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.tat21.crypto.ui.service.TestDataReader.getHubURL;

public class RemoteDriver implements DriverFactory{

    private static WebDriver driver;

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            try {
                driver = new RemoteWebDriver(new URL(getHubURL()), CapabilitiesProvider.getCapabilities(System.getProperty("browser")));
            } catch (MalformedURLException e) {
                MyLogger.error(e.getMessage());
            }
        }
        driver.manage().window().maximize();
        return driver;
    }

    @Override
    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
