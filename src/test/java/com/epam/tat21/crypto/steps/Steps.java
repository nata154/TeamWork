package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.bo.User;
import com.epam.tat21.crypto.driver.DriverFactory;
import com.epam.tat21.crypto.driver.LocalDriver;
import com.epam.tat21.crypto.driver.RemoteDriver;
import com.epam.tat21.crypto.driver.RemoteDriverSauceLabs;
import com.epam.tat21.crypto.pages.*;
import com.epam.tat21.crypto.service.UserCreator;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;
    private UserAccountPage userAccountPage;
    private ExchangesPage exchangesPage;
    private NewsPage newsPage;
    private CoinsPage coinsPage;

    public DriverFactory getWebDriverFactory() {
        if (driver == null) {
            switch (System.getProperty("driver")) {
                case "local":
                    return new LocalDriver();
                case "remote":
                    return new RemoteDriver();
                case "sauce":
                    return new RemoteDriverSauceLabs();
            }
        }
        return new LocalDriver();
    }

    public void openBrowser() {
        driver = getWebDriverFactory().getDriver();
    }

    public void closeBrowser() {
        getWebDriverFactory().closeDriver();
    }

    public MainCryptoComparePage loginUser() {
        User user = UserCreator.withCredentialsFromProperty();
        return new MainCryptoComparePage(driver)
                .openPage()
                .login(user);
    }

    public UserAccountPage goToUserAccountProfile() {
        new HeaderPage(driver).getDropdownUserMenuInHeader().clickAccountLine();
        return new UserAccountPage(driver).clickTabGeneral();
    }

    public UserAccountPage changeAndSaveFirstNameSurnameInUserAccount(String firstNameToChange, String surnameToChange) {
        return new UserAccountPage(driver)
                .enterNewFirstNameAndSurname(firstNameToChange, surnameToChange)
                .clickButtonSaveChanges();
    }

    public String getInfoFromPopupWindowAfterSavingChangesInUserAccount() {
        String textOnPopupWindowAfterSavingChanges = new UserAccountPage(driver).getInfoFromPopupWindow();
        return textOnPopupWindowAfterSavingChanges;
    }

    public ExchangesPage openExchangePage() {
        return exchangesPage = new ExchangesPage(driver).
                openPage();
    }

    public ExchangesPage filterByCountry(Countries country) {
        return exchangesPage.
                clickOnCountryDropdown().
                selectCountryInDropdown(country).
                scrollPage();
    }

    public int getFromFilteredPageNumberOfResultsWith(Countries country) {
        return exchangesPage.getFromFilteredPageAllResultsWith(country).size();
    }

    public int getAllCountryLabelsFromFilteredPage() {
        return exchangesPage.getAllCountryLabelsFromFilteredPage().size();
    }

    public int getNumberOfExchangesFromCountryBadge() {
        return exchangesPage.getNumberOfExchangesInBadge();
    }

    public NewsPage openNewsPage() {
        return newsPage = new NewsPage(driver).
                openPage();
    }

    public NewsPage filterByCoin(Coin coin) {
        return newsPage.goToCoinNews(coin);
    }

    public int getAllCoinNewsFromFilteredPage(Coin coin) {
        return newsPage.getNumberOfNewsForCoin(coin);
    }


    public CoinsPage openCoinsPage() {
        return coinsPage = new CoinsPage(driver).
                openPage();
    }

//    public String getActualCurrencyForCoin(List<Coin> coin, List<Currency> currency) {
//////        List<WebElement> newsTitles = new CoinsPage(driver).openPage().getAllNewsArticleTitle();
//////        //get the text from news titles and fill an array by them
//////        return IntStream.range(0, 50).mapToObj(i -> newsTitles.get(i).getText()).toArray(String[]::new);
//////        return new CoinsPage(driver).selectCurrency(currency)
//////                .findCurrencyCostForCoin(coin, currency);
////
////    }

}
