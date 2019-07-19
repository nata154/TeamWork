package com.epam.tat21.crypto.mobile.tests;

import com.epam.testng.JIRATestKey;
import org.testng.annotations.Test;

public class CheckUpdatingUserAccountMobileTest extends PreConditionsOfMobileTest{

    private static final int COUNT_OF_GENERATED_POSTFIX_LETTERS = 3;

    @JIRATestKey(key = "EPMFARMATS-9610")
    @Test
    public void checkUpdatingUserAccountTest() {
        mobSteps.loginUser();
        mobSteps.clickUserAccount();

//        Assert.assertEquals("Wrong text in pop-up after saving changes in user general tab",
//                "Wrong text in pop-up after saving changes in user general tab");
    }
}
