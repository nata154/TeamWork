package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.apiutils.ResponseUtils;
import com.epam.tat21.crypto.api.model.CoinValueResponse;
import com.epam.tat21.crypto.api.model.LatestNews;
import com.epam.tat21.crypto.api.model.NewsItem;
import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ApiSteps {

    public Response getResponseWithLatestNews() {
        MyLogger.info("Getting response with latest news");
        return RestAssured.when().get(TestDataReader.getApiGetUrl() + "v2/news/").andReturn();
    }

    public Response getResponseWithNewsByCoin(Coin coin) {
        MyLogger.info("Getting response with news by coin");
        return RestAssured.when().get(TestDataReader.getApiGetUrl() +
                "v2/news/?categories=" + coin.getAbbreviationCoin()).andReturn();
    }

    private LatestNews getLatestNewsFromResponse(Response response) throws IOException {
        MyLogger.info("Filling model classes LatestNews -> NewsItem -> NewsItemSourceInfo");
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(response, LatestNews.class);
    }

    public String[] getLatestNewsTitleItems(int numberOfItems) throws IOException {
        MyLogger.info("Getting " + numberOfItems + " latest news titles from the response");
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithLatestNews());
        List<NewsItem> sortedNews = latestNews.getSortedData();
        //return subarray of titles, cause the news page can contain 50 news, while JSON can contain 100
        return IntStream.range(0, numberOfItems).mapToObj(i -> sortedNews.get(i).getTitle()).toArray(String[]::new);
    }

    public String[] getCoinNewsTitleItems(int numberOfItems, Coin coin) throws IOException {
        MyLogger.info("Getting " + numberOfItems + " coin news titles from the response");
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithNewsByCoin(coin));
        List<NewsItem> sortedNews = latestNews.getSortedData();
        //return subarray of titles, cause the news page can contain 50 news, while JSON can contain 100
        return IntStream.range(0, numberOfItems).mapToObj(i -> sortedNews.get(i).getTitle()).toArray(String[]::new);
    }


    public Response getResponseWithCoinCostInCurrency() {
        MyLogger.info("Getting response with currency costs for coins");
        return RestAssured.when().get(TestDataReader.getApiGetUrl() + "pricemulti?fsyms=LTC,BTC&tsyms=EUR,JPY").andReturn();//убрать хардкод
    }

    public CoinValueResponse getCoinsValueInCurrency(Response response) throws IOException {
        MyLogger.info("Filling model classes CoinValueInCurrency -> CurrencyForCoin");
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(response, CoinValueResponse.class);
    }

//    public String[] getCoinsValueInCurrencyItemsAsArray() throws IOException {
//        MyLogger.info("Getting coin costs in currency");
//        CoinValueInCurrency coinsValueInCurrency = getCoinsValueInCurrency(getResponseWithCoinCostInCurrency());
//        List<CoinValueInCurrency> coinsValueInCurrencyItemsAsArray = Arrays.asList(coinsValueInCurrency);
//        //return subarray of titles, cause the news page can contain 50 news, while JSON can contain 100
//        return IntStream.range(0, 10).mapToObj(i -> coinsValueInCurrencyItemsAsArray.get(i)).toArray(String[]::new);
//    }
}