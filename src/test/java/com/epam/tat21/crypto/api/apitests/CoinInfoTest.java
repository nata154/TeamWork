package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.annotations.Test;

public class CoinInfoTest extends CommonConditions {

    @Test
    public void getCoinInfoTest() {
        apiSteps.getResponseWithCoinsInfo();
        apiSteps.getCoinInfo();
    }

}
