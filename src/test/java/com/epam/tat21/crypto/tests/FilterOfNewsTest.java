package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.bo.Coin;
import org.testng.Assert;
import org.testng.annotations.Test;

public class FilterOfNewsTest extends CommonConditions {

    private static final int COUNT_OF_NEWS = 50;

    @Test
    public void checkFilterNewsByBitcoinTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.BTC), COUNT_OF_NEWS);
    }

    @Test
    public void checkFilterNewsByEthereumTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.ETH), COUNT_OF_NEWS);
    }

    @Test
    public void checkFilterNewsByLitecoinTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.LTC), COUNT_OF_NEWS);
    }

    @Test
    public void checkFilterNewsByMoneroTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.XMR), COUNT_OF_NEWS);
    }

    @Test
    public void checkFilterNewsByZCashTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.ZEC), COUNT_OF_NEWS);
    }

    @Test
    public void checkFilterNewsByXRPTest() {
        Assert.assertEquals(steps.checkFilterNewsByCoin(Coin.XRP), COUNT_OF_NEWS);
    }
}
