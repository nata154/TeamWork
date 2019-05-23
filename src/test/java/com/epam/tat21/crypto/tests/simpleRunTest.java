package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.driver.DriverSingleton;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
        import org.testng.annotations.AfterClass;
        import org.testng.annotations.BeforeClass;
        import org.testng.annotations.Test;

public class simpleRunTest {

    private WebDriver driver;
    private static final String TEAMWORK_APP_URL = "https://www.cryptocompare.com";
    private static String APP_TITLE = "CryptoCompare.com - Live cryptocurrency prices, trades, volumes, forums, wallets, mining equipment, and reviews | CryptoCompare.com";

    @BeforeClass
    public void setUp()
    {
        driver = DriverSingleton.getDriver();
        driver.get(TEAMWORK_APP_URL);
    }

    @Test
    public void SimpleRunTest() {
        Assert.assertEquals(driver.getTitle(),APP_TITLE);
    }

    @AfterClass
    public void tearDown() {
        driver.quit();
    }

}
