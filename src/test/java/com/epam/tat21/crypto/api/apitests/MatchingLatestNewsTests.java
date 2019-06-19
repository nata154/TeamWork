package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.data.CoinsDataProvider;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class MatchingLatestNewsTests extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9289")
    @Test
    public void matchingLatestNewsInResponseAndOnPageByTitleTest() throws IOException {
        steps.openNewsPage();
        String[] newsTitlesFromResponse = apiSteps.getLatestNewsTitleItems(50);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage(50);
        Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));
    }

    @JIRATestKey(key = "EPMFARMATS-9296")
    @Test(dataProvider = "coinsForTests", dataProviderClass = CoinsDataProvider.class)
    public void matchingNewsInResponseAndOnPageByCoinTest(Coin coin) throws IOException {
        steps.openNewsPage();
        steps.filterByCoin(coin);
        String[] newsTitlesFromResponse = apiSteps.getCoinNewsTitleItems(50, coin);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage(50);
        Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));
    }

}
