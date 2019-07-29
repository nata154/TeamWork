package com.epam.tat21.crypto.mobile.tests;

import com.epam.tat21.crypto.mobile.data.FeedsNamesMobileProvider;
import com.epam.tat21.crypto.mobile.service.PreConditionsOfMobileTest;
import com.epam.testng.JIRATestKey;
import org.junit.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class NewsFeedsMobileTest extends PreConditionsOfMobileTest {

    @BeforeTest
    public void logInApp() {
        mobSteps.loginUser().goToNewsPage();
    }

    @JIRATestKey(key = "EPMFARMATS-9710")
    @Test(dataProvider = "feedsForNewsMobileTests", dataProviderClass = FeedsNamesMobileProvider.class)
    public void newsFeedsMobileTest(String feedName) {
        mobSteps.goToSourcesPageFromNewsPage()
                .clickOnConcreteFeed(feedName)
                .goBackToNewsPage();
        Assert.assertTrue(mobSteps.areNewsFilteredProperly(feedName));

    }

}
