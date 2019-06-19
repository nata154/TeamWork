package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import org.testng.annotations.Test;

import java.util.List;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {

    Coin coinLTC = Coin.LTC;
    Coin coinBTC = Coin.BTC;
    Currency currencyEUR = Currency.EUR;
    Currency currencyJPY = Currency.JPY;

    @Test
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest(List<Coin> coins, List<Currency> currencies) {
        steps.loginUser();
        steps.openCoinsPage();

        // steps.getActualCurrencyForCoin(coins, currencies);
        //apiSteps.getResponseWithCoinCostInCurrency();

    }
}

