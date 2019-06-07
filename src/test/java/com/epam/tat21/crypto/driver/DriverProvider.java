
package com.epam.tat21.crypto.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static com.epam.tat21.crypto.service.TestDataReader.getNodeURL;

public class DriverProvider {
    private static WebDriver driver;
    private static String nodeURL = getNodeURL();
    private static DesiredCapabilities capabilities;

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {

                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    driver = new FirefoxDriver();
                }
                break;
                case "chrome": {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
                break;
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    driver = new EdgeDriver();
                }
                break;
                default: {
                    WebDriverManager.chromedriver().setup();
                    driver = new ChromeDriver();
                }
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static WebDriver getRemoteDriver() {
        if (driver == null) {
            switch (System.getProperty("browser")) {
                case "firefox": {
                    WebDriverManager.firefoxdriver().setup();
                    capabilities = DesiredCapabilities.firefox();
                    capabilities.setBrowserName("firefox");
                }
                break;
                case "chrome": {
                    WebDriverManager.chromedriver().setup();
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setBrowserName("chrome");

                }
                break;
                case "edge": {
                    WebDriverManager.edgedriver().setup();
                    capabilities = DesiredCapabilities.edge();
                    capabilities.setBrowserName("edge");
                }
                break;
                default: {
                    WebDriverManager.chromedriver().setup();
                    capabilities = DesiredCapabilities.chrome();
                    capabilities.setBrowserName("chrome");
                }
            }
            try {
                capabilities.setPlatform(Platform.WINDOWS);
                driver = new RemoteWebDriver(new URL(nodeURL), capabilities);
                driver.manage().window().maximize();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
