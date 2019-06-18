package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.tests.CommonConditions;

public class MatchingCoinValueInCurrancyWithCommonCurrency extends CommonConditions {
        @Test
        public void matchingLatestNewsInResponseAndOnPageByTitleTest() throws IOException {
            steps.openNewsPage();
            apiSteps.getResponseWithCoinCostInCurrency();
            String[] newsTitlesFromPage = steps.get50LatestNewsTitleItemsFromPage();
            String[] newsTitlesFromResponse = apiSteps.get50LatestNewsTitleItems();
            Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));
        }
    }

}
