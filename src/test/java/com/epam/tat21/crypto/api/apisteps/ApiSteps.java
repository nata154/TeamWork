package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.model.ResponceCoinWrapper;
import com.epam.tat21.crypto.api.serializers.CoinJsonDeserializer;
import com.epam.tat21.crypto.utils.MyLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiSteps {

    public Response getResponseWithCoinsInfo() {
        RestAssured.baseURI = "https://min-api.cryptocompare.com/data/all/coinlist";
        MyLogger.info("Getting response with coins info");
        Response response = RestAssured.when().get().andReturn();
        return response;
    }

    public ResponceCoinWrapper getCoinInfo() {
        Response response = getResponseWithCoinsInfo();
        MyLogger.info("Filling model classes ResponceWrapper -> CoinModel -> DataCoinModel");
        return new CoinJsonDeserializer().deserialize(response);
    }
}
