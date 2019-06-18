package com.epam.tat21.crypto.api.serializers;

import com.epam.tat21.crypto.api.model.CoinModel;
import com.epam.tat21.crypto.api.model.DataCoinModel;
import com.epam.tat21.crypto.api.model.ResponceCoinWrapper;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class CoinJsonDeserializer { //extends JsonDeserializer<ResponceCoinWrapper> {

//----------JACKSON--------------
//    @Override
//    public ResponceCoinWrapper deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
//        ObjectCodec oc = jp.getCodec();
//        JsonNode node = oc.readTree(jp);
//
//        String response = node.get("Response").asText();
//        System.out.println(response);
//        String message = node.get("Message").asText();
//        System.out.println(message);
//        Iterator<Map.Entry<String,JsonNode>> mapOfCoins = node.get("Data").fields();
//
//        List<DataCoinModel> listOfCoins = new ArrayList<DataCoinModel>();
//
//        while(mapOfCoins.hasNext()) {
//            DataCoinModel coin = new DataCoinModel();
//            coin.setId(mapOfCoins.next().getValue().get("Id").asText());
//            System.out.println(mapOfCoins.next().getValue().get("Id").asText());
//            coin.setUrl(mapOfCoins.next().getValue().get("Url").asText());
//            coin.setName(mapOfCoins.next().getValue().get("Name").asText());
//            coin.setCoinName(mapOfCoins.next().getValue().get("CoinName").asText());
//            coin.setFullName(mapOfCoins.next().getValue().get("FullName").asText());
//            listOfCoins.add(coin);
//        }
//
//        CoinModel coinModel = new CoinModel(listOfCoins);
//        return new ResponceCoinWrapper(response, message, coinModel);
//    }

    public ResponceCoinWrapper deserialize(Response response) {
        JSONParser parser = new JSONParser();
        try {
            Object obj = parser.parse(response.getBody().asString());

            JSONObject jsonObject = (JSONObject) obj;
            String resp = (String) jsonObject.get("Response");
            System.out.println(resp);

            String message = (String) jsonObject.get("Message");
            System.out.println(message);

            JSONObject data = (JSONObject) jsonObject.get("Data");
            Collection<JSONObject> collectionData = data.values();


            List<DataCoinModel> listOfCoins = new ArrayList<>();

            for (JSONObject item : collectionData) {
                DataCoinModel dataCoin = new DataCoinModel();
                dataCoin.setId((String) item.get("Id"));
                dataCoin.setUrl((String) item.get("Url"));
                dataCoin.setName((String) item.get("Name"));
                dataCoin.setFullName((String) item.get("FullName"));
                dataCoin.setFullName((String) item.get("FullName"));
                listOfCoins.add(dataCoin);
            }

            CoinModel coinModel = new CoinModel(listOfCoins);

            ResponceCoinWrapper responceCoinWrapper = new ResponceCoinWrapper(resp, message, coinModel);
            return responceCoinWrapper;
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}



