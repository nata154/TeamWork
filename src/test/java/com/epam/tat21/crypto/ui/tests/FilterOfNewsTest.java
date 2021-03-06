package com.epam.tat21.crypto.ui.tests;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.data.CoinsDataProvider;
import com.epam.tat21.crypto.ui.service.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterOfNewsTest extends CommonConditions {

    private static final int COUNT_OF_NEWS = 50;

    @JIRATestKey(key = "EPMFARMATS-9111")
    @Test(dataProvider = "coinsForTests", dataProviderClass = CoinsDataProvider.class)
    public void checkFilterNewsByCoinTest(Coin coin) {
        steps.openNewsPage();
        steps.filterByCoin(coin);
        int numberOfResultWithCoin = steps.getAllCoinNewsFromFilteredPage(coin);
        Assert.assertEquals(numberOfResultWithCoin, COUNT_OF_NEWS);
    }
}
