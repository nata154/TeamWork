package com.epam.tat21.crypto.ui.driver;

import org.openqa.selenium.WebDriver;

public interface DriverFactory {

    WebDriver getDriver();

    void closeDriver();
}
