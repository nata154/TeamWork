package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.service.TestDataReader;
import org.openqa.selenium.WebDriver;

public class PortfolioPage extends BasePage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "portfolio/";

    public PortfolioPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public PortfolioPage openPage() {
        driver.get(BASE_URL);
        return this;
    }
}
