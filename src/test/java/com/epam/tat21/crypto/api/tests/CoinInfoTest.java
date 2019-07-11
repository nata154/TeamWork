package com.epam.tat21.crypto.api.tests;

import com.epam.tat21.crypto.ui.tests.CommonConditions;
import org.testng.annotations.Test;

import java.io.IOException;

public class CoinInfoTest extends CommonConditions {

    @Test
    public void getCoinInfoTest() throws IOException {
        apiSteps.getResponseWithCoinsInfo();
        apiSteps.getCoinInfo();
    }
}
