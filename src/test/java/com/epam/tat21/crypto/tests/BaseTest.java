package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.driver.DriverProvider;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;

public class BaseTest {

    protected WebDriver driver;
    private static final String TEAMWORK_APP_URL = "https://www.cryptocompare.com";


    @BeforeClass
    public void setUp()
    {
        driver = DriverProvider.getDriver();
        driver.get(TEAMWORK_APP_URL);
    }

    @AfterClass
    public void tearDown() {

        DriverProvider.closeDriver();
    }

}
