package com.epam.tat21.crypto.pages;

import org.openqa.selenium.WebDriver;

public class PortfolioPage extends BasePage {

    private final String BASE_URL = "https://www.cryptocompare.com/portfolio/";

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PortfolioPage openPage() {
        driver.get(BASE_URL);
        return this;
    }
}
