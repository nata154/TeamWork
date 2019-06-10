package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.data.CoinsDataProvider;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterOfNewsTest extends CommonConditions {

    private static final int COUNT_OF_NEWS = 50;

    @Test(dataProvider = "coinsForTests", dataProviderClass = CoinsDataProvider.class)
    public void checkFilterNewsByCoinTest(Coin coin) {
        steps.openNewsPage();
        steps.filterByCoin(coin);
        int numberOfResultWithCoin = steps.getAllCoinNewsFromFilteredPage(coin);
        Assert.assertEquals(numberOfResultWithCoin, COUNT_OF_NEWS);
    }
}
