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
        String[] newsTitlesFromResponse = apiSteps.getLatestNewsTitleItems(50);
        String[] newsTitlesFromPage = steps.getLatestNewsTitleItemsFromPage(50);
        Assert.assertTrue(Arrays.equals(newsTitlesFromResponse, newsTitlesFromPage));
    }

}
