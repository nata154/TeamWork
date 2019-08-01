package com.epam.tat21.crypto.ui.tests;

import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.service.CommonConditions;
import com.epam.tat21.crypto.ui.utils.RandomString;
import com.epam.testng.JIRATestKey;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class CoinInUserPortfolioTest extends CommonConditions {

    private static final int COUNT_OF_SYMBOLS = 5;
    private static final int LIMIT = 1000;
    private Coin coin = Coin.BTC;
    private String currency = coin.getAbbreviationCoin();
    private String changedAmount = RandomString.getRandomNumber(LIMIT);
    private String price = RandomString.getRandomNumber(LIMIT);
    private String description = RandomString.getRandomString(COUNT_OF_SYMBOLS);


    @BeforeClass
    public void logIn() {
        steps.loginUser();
    }

    @JIRATestKey(key = "EPMFARMATS-9267")
    @Test
    public void coinAddingTest() {
        String name = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        String amount = RandomString.getRandomNumber(LIMIT);
        steps.createUserPortfolio(name, currency, description);
        steps.addCoinToUserPortfolio(coin, amount, price);
        assertTrue(steps.isCoinAdded());
        steps.deleteUserPortfolio();
    }

    @JIRATestKey(key = "EPMFARMATS-9271")
    @Test
    public void coinEditingTest() {
        String name = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        String amount = RandomString.getRandomNumber(LIMIT);
        steps.createUserPortfolio(name, currency, description);
        steps.addCoinToUserPortfolio(coin, amount, price);
        steps.changeAmountOfCoins(changedAmount);
        assertTrue(steps.isAmountOfCoinChanged());
        steps.deleteUserPortfolio();
    }

    @JIRATestKey(key = "EPMFARMATS-9268")
    @Test
    public void coinDeletingTest() {
        String name = RandomString.getRandomString(COUNT_OF_SYMBOLS);
        String amount = RandomString.getRandomNumber(LIMIT);
        steps.createUserPortfolio(name, currency, description);
        steps.addCoinToUserPortfolio(coin, amount, price);
        steps.deleteCoinFromUserPortfolio();
        assertTrue(steps.isCoinDelete());
        steps.deleteUserPortfolio();
    }

}

