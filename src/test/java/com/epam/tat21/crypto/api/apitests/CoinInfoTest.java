package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.api.model.ResponceCoinWrapper;
import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.annotations.Test;

import java.io.IOException;

public class CoinInfoTest extends CommonConditions {

    @Test
    public void getCoinInfoTest() throws IOException {
        apiSteps.getResponseWithCoinsInfo();
        ResponceCoinWrapper wrapper = apiSteps.getCoinInfo();
    }
}
