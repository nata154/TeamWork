package com.epam.tat21.crypto.steps;

import com.epam.tat21.crypto.driver.DriverProvider;
import com.epam.tat21.crypto.pages.NewsPage;
import org.openqa.selenium.WebDriver;

public class Steps {

    private WebDriver driver;

    public void openBrowser(String url) {
        driver = DriverProvider.getDriver();
        driver.get(url);
    }

    public void closeBrowser() {
        DriverProvider.closeDriver();
    }

    public boolean checkFilterNewsByBitcoin() {
        return new NewsPage(driver).goToBitcoinNews().checkCategoriesOfNewsBitcoin();
    }

    public boolean checkFilterNewsByEthereum() {
        return new NewsPage(driver).goToEthereumNews().checkCategoriesOfNewsEthereum();
    }

    public boolean checkFilterNewsByLitecoin() {
        return new NewsPage(driver).goToLitecoinNews().checkCategoriesOfNewsLitecoin();
    }

    public boolean checkFilterNewsByMonero() {
        return new NewsPage(driver).goToMoneroNews().checkCategoriesOfNewsMonero();
    }

    public boolean checkFilterNewsByZCash() {
        return new NewsPage(driver).goToZCashNews().checkCategoriesOfNewsZCash();
    }

    public boolean checkFilterNewsByXRP() {
        return new NewsPage(driver).goToXRPNews().checkCategoriesOfNewsXRP();
    }

}
