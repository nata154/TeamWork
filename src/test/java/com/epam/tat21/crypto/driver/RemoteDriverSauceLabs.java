package com.epam.tat21.crypto.driver;

import com.epam.tat21.crypto.utils.TestListener;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.tat21.crypto.service.TestDataReader.getSauceAccessKey;
import static com.epam.tat21.crypto.service.TestDataReader.getSauceUsername;

public class RemoteDriverSauceLabs implements DriverFactory {

    private static WebDriver driver;
    private static URL url;
    private static DesiredCapabilities capabilities;
    private static String sauceURL = "@ondemand.eu-central-1.saucelabs.com/wd/hub";

    public RemoteDriverSauceLabs() {
        this.capabilities = CapabilitiesProvider.getCapabilities(System.getProperty("browser"));
        capabilities.setCapability("version", "latest");
        capabilities.setCapability("screen-resolution", "1600x1200");
        capabilities.setCapability("name", "crypto-compare-test-" + TestListener.getCurrentTimeAsString());

    }

    public RemoteDriverSauceLabs(URL url, DesiredCapabilities capabilities) {
        this.url = url;
        this.capabilities = capabilities;
    }

    @Override
    public WebDriver getDriver() {
        if (driver == null) {
            try {
                driver = new RemoteWebDriver(new URL("http://" + getSauceUsername() + ":" +
                        getSauceAccessKey() + sauceURL), capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
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
