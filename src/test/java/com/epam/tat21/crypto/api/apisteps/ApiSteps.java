package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.model.ResponceCoinWrapper;
import com.epam.tat21.crypto.api.serializers.CoinJsonDeserializer;
import com.epam.tat21.crypto.utils.MyLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiSteps {

    public Response getResponseWithCoinsInfo() {
        //RestAssured.baseURI = TestDataReader.getApiGetUrl() + "all/coinlist";   //DON't WORK TARGET HOST IS NULL
        RestAssured.baseURI = "https://min-api.cryptocompare.com/data/all/coinlist";
        MyLogger.info("Getting response with coins info");
        Response response = RestAssured.when().get().andReturn();
        return response;
    }
//----------JACKSON--------------
//    public ResponceCoinWrapper getCoinsInfoFromResponse() throws IOException {
//        MyLogger.info("Filling model classes ResponceWrapper -> CoinModel -> DataCoinModel");
//        ObjectMapper mapper = new ObjectMapper();
//        SimpleModule module = new SimpleModule();
//        module.addDeserializer(ResponceCoinWrapper.class, new CoinJsonDeserializer());
//        mapper.registerModule(module);
//        responceCoinWrapper = mapper.readValue(response.getBody().asString(), ResponceCoinWrapper.class);
//        return responceCoinWrapper;
//    }

//-----------JSON-------
    public ResponceCoinWrapper getCoinInfo() {
        Response response = getResponseWithCoinsInfo();
        MyLogger.info("Filling model classes ResponceWrapper -> CoinModel -> DataCoinModel");
        return new CoinJsonDeserializer().deserialize(response);
    }
}
