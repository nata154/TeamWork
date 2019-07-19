package com.epam.tat21.crypto.mobile.tests;

import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CryptoLoginLogOutMobileTest extends PreConditionsOfMobileTest {

    @JIRATestKey(key = "EPMFARMATS-9639")
    @Test
    public void checkUpdatingUserAccountTest() {
        mobSteps.loginUser();
        mobSteps.clickUserAccountAndLogOut();
        Assert.assertTrue(mobSteps.checkLogout());
    }
}
