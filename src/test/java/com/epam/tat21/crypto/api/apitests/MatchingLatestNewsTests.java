package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;

public class MatchingLatestNewsTests extends CommonConditions {

    @Test
    public void matchingLatestNewsInResponseAndOnPageByTitleTest() throws IOException {
        steps.openNewsPage();
        apiSteps.getResponseWithLatestNews();
        apiSteps.getLatestNewsFromResponse();
        String[] newsTitlesFromPage = steps.get50LatestNewsTitleItemsFromPage();
        String[] newsTitlesFromResponse = apiSteps.get50LatestNewsTitleItems();
        Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));
    }
}
