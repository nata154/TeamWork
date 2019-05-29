package com.epam.tat21.crypto.pages;

import org.openqa.selenium.WebDriver;

public class ExchangesPage extends BasePage {

    private final String BASE_URL = "https://www.cryptocompare.com/exchanges/";

    public ExchangesPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public ExchangesPage openPage() {
        driver.get(BASE_URL);
        return this;
    }
}
