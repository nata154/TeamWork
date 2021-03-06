package com.epam.tat21.crypto.ui.driver;

import org.openqa.selenium.WebDriver;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
public class DriverManager {

    private static ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @Bean
    @Scope("prototype")
    public static synchronized WebDriver getDriver() {
        if (driver.get() == null) {
            setDriver();
        }
        return driver.get();
    }

    private static void setDriver() {
        DriverManager.driver.set(getWebDriverFactory().getDriver());
    }

    public static synchronized void closeDriver() {
        if (driver.get() != null) {
            driver.get().quit();
            driver.remove();
        }
    }

    private static DriverFactory getWebDriverFactory() {

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

}
