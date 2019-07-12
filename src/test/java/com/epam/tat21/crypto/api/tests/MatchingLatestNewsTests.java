package com.epam.tat21.crypto.api.tests;

import com.epam.tat21.crypto.api.data.FeedsNamesAndIdProvider;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.data.CoinsDataProvider;
import com.epam.tat21.crypto.ui.tests.CommonConditions;
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
        String[] newsTitlesFromResponse = apiSteps.getLatestNewsTitleItems();
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage();
        Assert.assertTrue(Arrays.equals(newsTitlesFromPage, newsTitlesFromResponse));
    }

    @JIRATestKey(key = "EPMFARMATS-9296")
    @Test(dataProvider = "coinsForTests", dataProviderClass = CoinsDataProvider.class)
    public void matchingNewsInResponseAndOnPageByCoinTest(Coin coin) throws IOException {
        steps.openNewsPage();
        steps.filterByCoin(coin);
        String[] newsTitlesFromResponse = apiSteps.getCoinNewsTitleItems(coin);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage();
        Assert.assertTrue(Arrays.equals(newsTitlesFromPage, newsTitlesFromResponse));
    }

    @JIRATestKey(key = "EPMFARMATS-9341")
    @Test(dataProvider = "namesAndIdForFeedsTests", dataProviderClass = FeedsNamesAndIdProvider.class)
    public void matchingNewsByFeedsTest(String feedId, String feedName) throws IOException {
        steps.openNewsPage()
                .clickOnFeedsDropdown()
                .clickOnFeedLink(feedName);
        String[] newsTitlesFromResponse = apiSteps.getFeedsNewsTitleItems(feedId);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage();
        Assert.assertTrue(Arrays.equals(newsTitlesFromPage, newsTitlesFromResponse));
    }
}
