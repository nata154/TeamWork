package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Countries;
import com.epam.tat21.crypto.driver.DriverProvider;
import com.epam.tat21.crypto.pages.ExchangesPage;
import com.epam.tat21.crypto.pages.NewsPage;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;
    private ExchangesPage exchangesPage;

    public void openBrowser() {
        driver = DriverProvider.getDriver();
    }

    public void closeBrowser() {
        DriverProvider.closeDriver();
    }

    public int checkFilterNewsByCoin(Coin coin) {
        return new NewsPage(driver).openPage().goToCoinNews(coin).getNumberOfNewsForCoin(coin);
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

}
