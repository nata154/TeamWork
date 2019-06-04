package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.utils.RandomString;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckUpdatingUserAccountTest extends CommonConditions {

    private static final int COUNT_OF_GENERATED_POSTFIX_LETTERS = 3;

    @Test
    public void checkUpdatingUserAccountTest() {
        String firstNameToChange = RandomString.generateRandomUserFirstName(COUNT_OF_GENERATED_POSTFIX_LETTERS);
        String surnameToChange = RandomString.generateRandomUserSurname(COUNT_OF_GENERATED_POSTFIX_LETTERS);
        String expectedInfoInPopupAfterSavingChangesInUserAccountWhenSuccessfully = "Successfully updated your details";

        steps.loginUser();
        steps.goToUserAccountProfile();
        steps.changeAndSaveFirstNameSurnameInUserAccount(firstNameToChange, surnameToChange);
        String actualInfoInPopupAfterSavingChangesInUserGeneralTab = steps.getInfoFromPopupWindowAfterSavingChangesInUserAccount();

        Assert.assertEquals(actualInfoInPopupAfterSavingChangesInUserGeneralTab, expectedInfoInPopupAfterSavingChangesInUserAccountWhenSuccessfully,
                "Wrong text in pop-up after saving Changes In User General Tab");
    }
}
