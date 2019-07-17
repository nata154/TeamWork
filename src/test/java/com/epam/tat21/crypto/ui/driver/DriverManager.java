package com.epam.tat21.crypto.ui.driver;

import org.openqa.selenium.WebDriver;

public class DriverManager {

    private static WebDriver driver;

    public static DriverFactory getWebDriverFactory() {
        if (driver == null) {
            switch (System.getProperty("driver")) {
                case "local":
                    return new LocalDriver();
                case "remote":
                    return new RemoteDriver();
                case "sauce":
                    return new RemoteDriverSauceLabs();
                default:
                    return new LocalDriver();
            }
        }
        return new LocalDriver();
    }

}
