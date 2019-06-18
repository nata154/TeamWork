package com.epam.tat21.crypto.pages;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import org.openqa.selenium.WebDriver;

import java.util.List;

public class CoinsPage extends HeaderPage {

    private final String BASE_URL = TestDataReader.getApplicationUrl() + "coins/list/";

    public CoinsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public CoinsPage openPage() {
        driver.get(BASE_URL);
        MyLogger.info("CoinsPage was opened");
        return this;
    }

    public CoinsPage selectCoin(List<Coin> coins) {

    }
}
