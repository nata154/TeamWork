package com.epam.tat21.crypto.bo;

import com.epam.tat21.crypto.pages.HeaderPage;
import com.epam.tat21.crypto.utils.CoinValueParser;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;

public class PortfolioItem extends HeaderPage {

    private final String COIN_NAME_LINK_LOCATOR = "//div[@class='table-col col-name']//a";
    private final String COIN_VALUE_LOCATOR = "//div[@class='responsive-value']/span";

    public PortfolioItem(WebDriver driver) {
        super(driver);
    }

    public List<WebElement> getCoinNamesList() {
        return driver.findElements(By.xpath(COIN_NAME_LINK_LOCATOR));
    }

    public List<WebElement> getCoinValuesList() {
        return driver.findElements(By.xpath(COIN_VALUE_LOCATOR));
    }


    public double getTotalValue() {
        double totalValue = 0;
        if (getCoinItemsList().size() > 0) {
            for (int i = 0; i < getCoinItemsList().size(); ++i) {
                totalValue += CoinValueParser.parseTotalCoinValue(getCoinItemsList().get(i).getCoinTotalValue());
            }
        }
        return totalValue;
    }

    public List<CoinItem> getCoinItemsList() {
        List<CoinItem> coinItemsList = new ArrayList<>();
        String coinName;
        String coinValue;
        for (int i = 0; i < getCoinNamesList().size() - 1; ++i) {
            if (getCoinNamesList().get(i).isDisplayed()) {
                coinName = getCoinNamesList().get(i).getText();
                coinValue = getCoinValuesList().get(i).getText();
                coinItemsList.add(new CoinItem(coinName, coinValue));
            }
        }
        return coinItemsList;
    }
}
