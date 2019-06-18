package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.model.CoinValueInCurrency;
import com.epam.tat21.crypto.utils.MyLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;

public class ApiSteps {

    private Response response;
    private CoinValueInCurrency listCoinCostCurrency;

    public Response getResponseWithCoinCostInCurrency() {
        RestAssured.baseURI = "https://min-api.cryptocompare.com/data/pricemulti?fsyms=BTC,ETH&tsyms=USD,EUR";
        MyLogger.info("Getting response about coins value in currency");
        response = RestAssured.when().get().andReturn();
        return response;
    }

    public CoinValueInCurrency getCoinCostInCurrencyFromResponse() throws IOException {
        MyLogger.info("Filling model classes CoinValueInCurrency -> CurrencyForCoin");
        //with Jackson library serialize a tree of model classes
        listCoinCostCurrency = new ObjectMapper().readValue(response.getBody().asString(), CoinValueInCurrency.class);
        return listCoinCostCurrency;
    }

    }


