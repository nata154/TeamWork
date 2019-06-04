package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.driver.DriverProvider;
import com.epam.tat21.crypto.pages.HeaderPage;
import com.epam.tat21.crypto.pages.MainCryptoComparePage;
import com.epam.tat21.crypto.pages.NewsPage;
import com.epam.tat21.crypto.pages.UserAccountPage;
import com.epam.tat21.crypto.service.UserCreator;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;
    private UserAccountPage userAccountPage;

    public void openBrowser() {
        driver = DriverProvider.getDriver();
    }

    public void closeBrowser() {
        DriverProvider.closeDriver();
    }

    public int checkFilterNewsByCoin(Coin coin) {
        return new NewsPage(driver).openPage().goToCoinNews(coin).getNumberOfNewsForCoin(coin);
    }

    public MainCryptoComparePage loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new MainCryptoComparePage(driver)
                .openPage()
                .login(user);
    }

    public UserAccountPage goToUserAccountProfile() {
        new HeaderPage(driver).getDropdownUserMenuInHeader().clickAccountLine();
        return new UserAccountPage(driver).clickTabGeneralInUserAccount();
    }

    public UserAccountPage changeAndSaveFirstNameSurnameInUserAccount(String firstNameToChange, String surnameToChange) {
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurnameInUserAccount(firstNameToChange, surnameToChange)
                .clickButtonSaveChangesInUserAccount();
    }

    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        String textOnPopupWindowAfterSavingUpdated = new UserAccountPage(driver).getInfoFromPopupWindowAfterSavingChangesInUserAccount();
        return textOnPopupWindowAfterSavingUpdated;
    }
}
