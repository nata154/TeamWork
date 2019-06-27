package com.epam.tat21.crypto.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements DriverFactory {

    private static WebDriver driver;

    @Override
    public WebDriver getDriver() {
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
