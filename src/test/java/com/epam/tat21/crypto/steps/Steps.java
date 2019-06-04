package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.driver.DriverProvider;
import com.epam.tat21.crypto.pages.NewsPage;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;

    public void openBrowser() {
        driver = DriverProvider.getDriver();
    }

    public void closeBrowser() {
        DriverProvider.closeDriver();
    }

    public int checkFilterNewsByCoin(Coin coin) {
        return new NewsPage(driver).openPage().goToCoinNews(coin).getNumberOfNewsForCoin(coin);
    }

    public WebDriver getDriver() {
        return this.driver;
    }
}
