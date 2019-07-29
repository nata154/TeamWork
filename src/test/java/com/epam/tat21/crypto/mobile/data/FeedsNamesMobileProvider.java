package com.epam.tat21.crypto.mobile.data;

import org.testng.annotations.DataProvider;

public class FeedsNamesMobileProvider {

    private FeedsNamesMobileProvider() {
    }

    @DataProvider(name = "feedsForNewsMobileTests")
    public static Object[][] feedsForNewsMobileTest() {

        Object[][] toReturn = new Object[FeedsNamesMobile.values().length][];
        int i = 0;
        for (FeedsNamesMobile feed : FeedsNamesMobile.values()) {
            toReturn[i] = new Object[1];
            toReturn[i][0] = feed.getFeedName();
            i++;
        }
        return toReturn;
    }

}
