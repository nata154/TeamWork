package com.epam.tat21.crypto.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class VerifyApplicationTitleTest extends BaseTest{
    private static String APP_TITLE = "CryptoCompare.com - Live cryptocurrency prices, trades, volumes, forums, wallets, mining equipment, and reviews | CryptoCompare.com";

    @Test
    public void verifyApplicationTitleTest() {
        Assert.assertEquals(driver.getTitle(),APP_TITLE);
    }
}
