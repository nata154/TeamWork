package com.epam.tat21.crypto.ui.driver;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class LocalDriver implements DriverFactory {

    private WebDriver driver;

    @Override
    public WebDriver getDriver() {

        if (driver == null) {
            System.out.println("GET DRIVER " + Thread.currentThread().getName().toUpperCase());
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
        System.out.println("RETURNING THE SAME DRIVER " + Thread.currentThread().getName().toUpperCase());
        driver.manage().window().maximize();
        return driver;
    }

    @Override
    public void closeDriver() {
        System.out.println("CLOSE DRIVER " + Thread.currentThread().getName().toUpperCase());
        driver.quit();
        driver = null;
    }
}
