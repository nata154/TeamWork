package com.epam.tat21.crypto.driver;

import org.openqa.selenium.WebDriver;

public interface DriverFactory {

    WebDriver getDriver();

    void closeDriver();
}
