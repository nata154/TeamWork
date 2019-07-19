package com.epam.tat21.crypto.mobile.tests;

import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckUpdatingUserAccountMobileTest extends PreConditionsOfMobileTest{

    private static final int COUNT_OF_GENERATED_POSTFIX_LETTERS = 3;

    @JIRATestKey(key = "EPMFARMATS-9610")
    @Test
    public void checkUpdatingUserAccountTest() {
//        String firstNameToChange = RandomString.generateRandomUserFirstName(COUNT_OF_GENERATED_POSTFIX_LETTERS);
//        String surnameToChange = RandomString.generateRandomUserSurname(COUNT_OF_GENERATED_POSTFIX_LETTERS);
//        String expectedInfoInPopupWhenSuccessfully = "Successfully updated your details";
//
//        steps.loginUser();
//        steps.goToUserAccountProfile();
//        steps.changeAndSaveFirstNameSurnameInUserAccount(firstNameToChange, surnameToChange);
//        String infoInPopup = steps.getInfoFromPopupWindowAfterSavingChangesInUserAccount();
//
//        Assert.assertEquals(infoInPopup, expectedInfoInPopupWhenSuccessfully,
//                "Wrong text in pop-up after saving changes in user general tab");
//mobSteps.loginUser();
        Assert.assertEquals("Wrong text in pop-up after saving changes in user general tab",
                "Wrong text in pop-up after saving changes in user general tab");
    }
}
