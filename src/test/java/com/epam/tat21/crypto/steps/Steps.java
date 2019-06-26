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
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.IntStream;

public class Steps {

    private WebDriver driver;
    private UserAccountPage userAccountPage;
    private ExchangesPage exchangesPage;
    private NewsPage newsPage;

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

    /**
     * The method below, if the page contains more than 50 titles,
     * returns a subarray of only 50 latest titles, because the api response
     * always contains only 50.
     */

    public String[] getLatestNewsTitleItemsFromPage() {
        List<WebElement> newsTitles = newsPage.getAllNewsArticleTitle();
        if (newsTitles.size() <= 50) {
            MyLogger.info("Getting " + newsTitles.size() + " news titles from page");
            //get the text from news titles, fill an array by them and replace from them two and more spaces and no-break spaces
            return newsTitles.stream().map(newsTitle -> newsTitle.getText().replaceAll("\\s{2,}|\\u00a0", " ")).toArray(String[]::new);
        } else {
            MyLogger.info("Getting only 50 news titles from page, because the page contains " + newsTitles.size());
            //get the text from news titles, fill a subarray by them and replace from them two and more spaces and no-break spaces
            return IntStream.range(0, 50).mapToObj((i -> newsTitles.get(i).getText().replaceAll("\\s{2,}|\\u00a0", " "))).toArray(String[]::new);
        }
    }
}
