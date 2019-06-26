package com.epam.tat21.crypto.api.apidata;

import com.epam.tat21.crypto.api.apisteps.ApiSteps;
import com.epam.tat21.crypto.utils.MyLogger;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class FeedsNamesAndKeysProvider {

    private static ApiSteps apiSteps = new ApiSteps();

    private FeedsNamesAndKeysProvider() {
    }

    @DataProvider(name = "namesAndKeysForFeedsTests")
    public static Object[][] namesAndKeysForFeeds() throws IOException {
        MyLogger.info("Initializing of the data provider: namesAndKeysForFeedsTests");
        String[] feedKeyQueries = apiSteps.getAllFeedsQueryKeys();
        String[] feedNames = apiSteps.getAllFeedsNames();
        Object[][] toReturn = new Object[feedKeyQueries.length][];
        int i = 0;
        for (String value : feedKeyQueries) {
            toReturn[i] = new Object[2];
            toReturn[i][0] = feedKeyQueries[i];
            toReturn[i][1] = feedNames[i];
            i++;
        }
        return toReturn;
    }

}
