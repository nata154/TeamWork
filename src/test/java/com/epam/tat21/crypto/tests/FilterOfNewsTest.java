package com.epam.tat21.crypto.tests;

import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterOfNewsTest extends CommonConditions {

    @Test
    public void checkFilterNewsByBitcoinTest() {
        Assert.assertTrue(steps.checkFilterNewsByBitcoin());
    }

    @Test
    public void checkFilterNewsByEthereumTest() {
        Assert.assertTrue(steps.checkFilterNewsByEthereum());
    }

    @Test
    public void checkFilterNewsByLitecoinTest() {
        Assert.assertTrue(steps.checkFilterNewsByLitecoin());
    }

    @Test
    public void checkFilterNewsByMoneroTest() {
        Assert.assertTrue(steps.checkFilterNewsByMonero());
    }

    @Test
    public void checkFilterNewsByZCashTest() {
        Assert.assertTrue(steps.checkFilterNewsByZCash());
    }

    @Test
    public void checkFilterNewsByXRPTest() {
        Assert.assertTrue(steps.checkFilterNewsByXRP());
    }
}
