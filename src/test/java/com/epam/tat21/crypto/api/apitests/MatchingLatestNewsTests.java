package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.api.apidata.FeedsNamesAndKeysProvider;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class MatchingLatestNewsTests extends CommonConditions {

   /* @JIRATestKey(key = "EPMFARMATS-9289")
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
    }*/

    @JIRATestKey(key = "EPMFARMATS-9341")
    @Test(dataProvider = "namesAndKeysForFeedsTests", dataProviderClass = FeedsNamesAndKeysProvider.class)
    public void matchingNewsByFeedsTest(String feedQueryKey, String feedName) throws IOException {
        steps.openNewsPage()
                .clickOnFeedsDropdown()
                .clickOnFeedLink(feedName);
        String[] newsTitlesFromResponse = apiSteps.getFeedsNewsTitleItems(feedQueryKey);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage();
        System.out.println(newsTitlesFromResponse.length + "/response/-" + newsTitlesFromPage.length + "/page/");
        /*for (String name : newsTitlesFromResponse) {
            System.out.print(name + "/r/");
        }
        System.out.println();
        for (String name : newsTitlesFromPage) {
            System.out.print(name + "/p/");
        }*/

        //Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));

        for (int i = 0; i < newsTitlesFromResponse.length; i++) {
            Assert.assertEquals(newsTitlesFromResponse[i], newsTitlesFromPage[i]);
        }
    }

}
