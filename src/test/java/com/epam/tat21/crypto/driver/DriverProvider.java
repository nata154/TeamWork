
package com.epam.tat21.crypto.driver;

import org.openqa.selenium.WebDriver;

public class DriverProvider {
    private static WebDriver driver;

    private DriverProvider() {
    }

    public static WebDriver getDriver() {
        if (driver == null) {
            switch (System.getProperty("driver")) {
                case "local":
                    driver = new LocalDriver().getDriver();
                    break;

                case "remote":
                    driver = new RemoteDriver().getDriver();
                    break;

                case "sauce":
                    driver = new RemoteDriverSauceLabs().getDriver();
                    break;
            }
            driver.manage().window().maximize();
        }
        return driver;
    }

    public static void closeDriver() {
        driver.quit();
        driver = null;
    }
}
