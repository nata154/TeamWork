package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class BaseTest {

    protected WebDriver driver;
    private static final String TEAMWORK_APP_URL = "https://www.cryptocompare.com";


    @BeforeClass
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
        driver.get(TEAMWORK_APP_URL);
    }

    @AfterClass
    public void tearDown() {

        DriverSingleton.closeDriver();
    }

}
