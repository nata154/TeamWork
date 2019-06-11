package com.epam.tat21.crypto.driver;

import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.tat21.crypto.service.TestDataReader.getHubURL;

public class RemoteDriver implements DriverFactory{

    private static WebDriver driver;
    private URL url;
    private DesiredCapabilities capabilities;

    public RemoteDriver() {
    }

    public RemoteDriver(URL url, DesiredCapabilities capabilities) {
        this.url = url;
        this.capabilities = capabilities;
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            try {
                driver = new RemoteWebDriver(new URL(getHubURL()), CapabilitiesProvider.getCapabilities(System.getProperty("browser")));
            } catch (MalformedURLException e) {
                MyLogger.error(e.getMessage());
            }
        }
        return driver;
    }

    @Override
    public void closeDriver() {
        driver.quit();
        driver = null;
    }
}
