package com.epam.tat21.crypto.mobile.tests;

import com.epam.testng.JIRATestKey;
import org.testng.annotations.Test;

public class CheckUpdatingUserAccountMobileTest extends PreConditionsOfMobileTest {

    @JIRATestKey(key = "EPMFARMATS-9610")
    @Test
    public void checkUpdatingUserAccountTest() {
        mobSteps.loginUser();
        //Cryptocompare application doesn't have such function to update user account
        mobSteps.clickUserAccountAndLogOut();
    }
}
