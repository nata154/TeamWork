package com.epam.tat21.crypto.steps;

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

    public boolean checkFilterNewsByBitcoin() {
        return new NewsPage(driver).openPage().goToBitcoinNews().checkCategoriesOfNewsBitcoin();
    }

    public boolean checkFilterNewsByEthereum() {
        return new NewsPage(driver).openPage().goToEthereumNews().checkCategoriesOfNewsEthereum();
    }

    public boolean checkFilterNewsByLitecoin() {
        return new NewsPage(driver).openPage().goToLitecoinNews().checkCategoriesOfNewsLitecoin();
    }

    public boolean checkFilterNewsByMonero() {
        return new NewsPage(driver).openPage().goToMoneroNews().checkCategoriesOfNewsMonero();
    }

    public boolean checkFilterNewsByZCash() {
        return new NewsPage(driver).openPage().goToZCashNews().checkCategoriesOfNewsZCash();
    }

    public boolean checkFilterNewsByXRP() {
        return new NewsPage(driver).openPage().goToXRPNews().checkCategoriesOfNewsXRP();
    }

}
