package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

public class MatchingCoinValueInCurrencyWithCommonCurrencyTest extends CommonConditions {


    @JIRATestKey(key = "EPMFARMATS-9340")
    @Test
//            (dataProvider = "coinsForTests", dataProviderClass = CoinsDataProvider.class)
    public void matchingCoinValueInCurrencyWithCommonCurrencyTest() throws IOException {

//        List<Coin> coins1 = new ArrayList<>();
//        List<Currency> currencies1 = new ArrayList<>();
//        coins1.add(Coin.LTC);
//        coins1.add(Coin.BTC);
//        coins1.add(Coin.XRP);
//        currencies1.add(Currency.EUR);
//        currencies1.add(Currency.JPY);
//        currencies1.add(Currency.USD);

//        Coin[] coinsFromEnum = Coin.values();
//        Currency[] currenciesFromEnum = Currency.values();
//
//        coins.add(coinsFromEnum);
//
//        String resultCoinsForQuery = apiSteps.getValuesFromEnum(coinsFromEnum);
//        String resultCurrenciesForQuery = apiSteps.getValuesFromEnum(currenciesFromEnum);
//        String resultQueryForGettingCurrenciesForCoins = apiSteps.getResultQueryForGettingCurrenciesForCoins(resultCoinsForQuery, resultCurrenciesForQuery);


        List<Coin> coins = Arrays.asList(Coin.values());
        List<Currency> currencies = Arrays.asList(Currency.values());
//        steps.openCoinsPage()
//                .selectCurrencyAndGetCostForCoins1(coins, currencies);


        steps.openCoinsPage()
                .selectCurrencyAndGetCostForCoins(coins, currencies);
//
        String resultCoinsForQuery = apiSteps.getResultCoinsForQuery(coins);
        String resultCurrenciesForQuery = apiSteps.getResultCurrenciesForQuery(currencies);
        String resultQueryForGettingCurrenciesForCoins = apiSteps.getResultQueryForGettingCurrenciesForCoins(resultCoinsForQuery, resultCurrenciesForQuery);
//
        Response response = apiSteps.getResponseWithCoinCostInCurrency(resultQueryForGettingCurrenciesForCoins);
        apiSteps.getCoinsValueInCurrency(response);
//
        double coinCostInCurrencyFromResponse = apiSteps.getCoinsValueInCurrency(response).getBtc().getJpy();
        //double coinCostInCurrencyFromPage = steps.getCoinCostInCurrencyFromPage(coins, currencies, "BTC", "JPY");

//
        System.out.println("coinCostInCurrencyFromResponse  - " + coinCostInCurrencyFromResponse);
        // System.out.println("coinCostInCurrencyFromPage  - " + coinCostInCurrencyFromPage);
//
//        double delta = (Math.abs(coinCostInCurrencyFromPage - coinCostInCurrencyFromResponse)) / coinCostInCurrencyFromResponse * 100;
//        System.out.println("delta - " + delta);
//
//        Assert.assertTrue(delta <= 0.5);


//        Map <String, Map<String, Double>> tempMap = new ObjectMapper().readValue(apiSteps.getResponseWithMultiPrice().getBody().asString(), Map.class);
//        for(Map.Entry<String, Map<String, Double>> item: tempMap.entrySet()){
//            System.out.println("Key: " + item.getKey() + "//");
//            for (Map.Entry<String, Double> map : item.getValue().entrySet()){
//                System.out.println("Inner key: " + map.getKey() + "//Inner Value: " + map.getValue());
//            }
//        }


    }
}
