package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.pages.HeaderPage;
import com.epam.tat21.crypto.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.pages.UserAccountPage;
import com.epam.tat21.crypto.service.UserCreator;
import org.testng.Assert;
import org.testng.annotations.Test;

public class CheckUpdatingUserAccountTest extends BaseTest {

    @Test
    public void checkUpdatingUserAccountTest() {
        String firstNameToChange = "natanata";
        String surnameToChange = "natanata";
        String popupAfterSavingChangesInUserGeneralTabWhenSuccessfully = "Successfully updated your details";

        User user = UserCreator.withCredentialsFromProperty();
        new MainCryptoComparePage(driver)
                .login(user);

        HeaderPage headerPage = new HeaderPage(driver);

      //  UserDropdownMenuInHeader userDropdownMenuInHeader = new UserDropdownMenuInHeader(driver);

        headerPage.getDropdownUserMenuInHeader().clickAccountLine();

        UserAccountPage userAccountPage = new UserAccountPage(driver);
        userAccountPage.clickTabGeneralInUserAccount()
                .enterNewFirstNameAndSurnameInTabGeneralInUserAccount(firstNameToChange, surnameToChange)
                .clickButtonSaveChangesInGeneralTabInUserAccount();

        Assert.assertEquals(userAccountPage.getInfoFromPopupWindowAfterSavingChangesInUserGeneralTab(), popupAfterSavingChangesInUserGeneralTabWhenSuccessfully,
                "Wrong text in pop-up after saving Changes In User General Tab");

    }
}
