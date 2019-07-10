package com.epam.tat21.crypto.api.apidata;

import com.epam.tat21.crypto.api.apisteps.ApiSteps;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import org.testng.annotations.DataProvider;

import java.io.IOException;

public class FeedsNamesAndIdProvider {

    private static ApiSteps apiSteps = new ApiSteps();

    private FeedsNamesAndIdProvider() {
    }

    @DataProvider(name = "namesAndIdForFeedsTests")
    public static Object[][] namesAndIdForFeeds() throws IOException {
        MyLogger.info("Initializing of the data provider: namesAndIdForFeedsTests");
        String[] feedId = apiSteps.getAllFeedsId();
        String[] feedNames = apiSteps.getAllFeedsNames();
        Object[][] toReturn = new Object[feedId.length][];
        int i = 0;
        for (String value : feedId) {
            toReturn[i] = new Object[2];
            toReturn[i][0] = feedId[i];
            toReturn[i][1] = feedNames[i];
            i++;
        }
        return toReturn;
    }

}
