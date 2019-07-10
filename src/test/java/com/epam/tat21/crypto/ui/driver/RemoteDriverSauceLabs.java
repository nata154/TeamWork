package com.epam.tat21.crypto.ui.driver;

import com.epam.tat21.crypto.ui.utils.DateUtils;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.tat21.crypto.ui.service.TestDataReader.getSauceAccessKey;
import static com.epam.tat21.crypto.ui.service.TestDataReader.getSauceUsername;

public class RemoteDriverSauceLabs implements DriverFactory {

    private static WebDriver driver;
    private DesiredCapabilities capabilities;
    private static String sauceURL = "@ondemand.eu-central-1.saucelabs.com/wd/hub";

    public RemoteDriverSauceLabs() {
        capabilities = CapabilitiesProvider.getCapabilities(System.getProperty("browser"));
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("screen-resolution", "1600x1200");
        capabilities.setCapability("name", "crypto-compare-test-" + DateUtils.getCurrentTimeAsString());
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            try {
                driver = new RemoteWebDriver(new URL("http://" + getSauceUsername() + ":" +
                        getSauceAccessKey() + sauceURL), capabilities);
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
