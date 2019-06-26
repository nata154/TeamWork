package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.api.apidata.FeedsNamesAndKeysProvider;
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
    @Test(dataProvider = "namesAndKeysForFeedsTests", dataProviderClass = FeedsNamesAndKeysProvider.class)
    public void matchingNewsByFeedsTest(String feedQueryKey, String feedName) throws IOException {
        steps.openNewsPage()
                .clickOnFeedsDropdown()
                .clickOnFeedLink(feedName);
        String[] newsTitlesFromResponse = apiSteps.getFeedsNewsTitleItems(feedQueryKey);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage();
        System.out.println(newsTitlesFromResponse.length + "/response/-" + newsTitlesFromPage.length + "/page/");
        Assert.assertTrue(Arrays.equals(newsTitlesFromPage, newsTitlesFromResponse));
    }
}
