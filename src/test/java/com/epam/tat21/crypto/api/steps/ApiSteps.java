package com.epam.tat21.crypto.api.steps;

import static com.epam.tat21.crypto.ui.service.GlobalConstants.REGEX_FOR_SPACES;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.epam.tat21.crypto.api.utils.ResponseUtils;
import com.epam.tat21.crypto.api.model.*;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.Currency;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiSteps {

    private static final String NEWS_RELATIVE_PATH = "v2/news/";
    private static final String FEEDS_RELATIVE_PATH = "news/feeds";
    private static final String COIN_LIST_RELATIVE_PATH = "/all/coinlist";
    private static final String MULTIPRICE_RELATIVE_PATH = "pricemulti";

    public ApiSteps() {
        RestAssured.baseURI = TestDataReader.getApiGetUrl();
    }

    public Response getResponseWithCoinsInfo() {
        MyLogger.info("Getting response with coins info");
        return RestAssured.when().get(COIN_LIST_RELATIVE_PATH).andReturn();
    }

    public ResponceCoinWrapper getCoinInfo() throws IOException {
        Response response = getResponseWithCoinsInfo();
        MyLogger.info("Filling model classes ResponceWrapper -> CoinModel -> DataCoinModel");
        return ResponseUtils.getObjectFromResponse(response, ResponceCoinWrapper.class);
    }

    public Response getResponseWithLatestNews() {
        MyLogger.info("Getting response with latest news");
        return RestAssured.when().get(NEWS_RELATIVE_PATH).andReturn();
    }

    public Response getResponseWithNewsByCoin(Coin coin) {
        MyLogger.info("Getting response with news by coin: " + coin.getAbbreviationCoin());
        return RestAssured.given().
                queryParam("categories", coin.getAbbreviationCoin()).
                when().get(NEWS_RELATIVE_PATH).andReturn();
    }

    public Response getResponseWithNewsByFeed(String feedId) {
        MyLogger.info("Getting response with news by " + feedId + " feed");
        return RestAssured.given().queryParam("feeds", feedId).
                when().get(NEWS_RELATIVE_PATH).andReturn();
    }

    public Response getResponseWithAllFeeds() {
        MyLogger.info("Getting response with feeds");
        return RestAssured.given().when().get(FEEDS_RELATIVE_PATH).andReturn();
    }

    private LatestNews getLatestNewsFromResponse(Response response) throws IOException {
        MyLogger.info("Filling model classes LatestNews -> NewsItem -> NewsItemSourceInfo");
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(response, LatestNews.class);
    }

    public String[] getLatestNewsTitleItems() throws IOException {
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithLatestNews());
        List<NewsItem> newsItems = latestNews.getData();
        MyLogger.info("Getting " + newsItems.size() + " latest news titles from the response");
        //return a sorted array of titles and replace from them two and more spaces and no-break spaces
        return newsItems.stream().map(newsItem -> newsItem.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).sorted().toArray(String[]::new);
    }

    public String[] getCoinNewsTitleItems(Coin coin) throws IOException {
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithNewsByCoin(coin));
        List<NewsItem> newsItems = latestNews.getData();
        MyLogger.info("Getting " + newsItems.size() + " coin news titles from the response");
        //return a sorted array of titles and replace from them two and more spaces and no-break spaces
        return newsItems.stream().map(newsItem -> newsItem.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).sorted().toArray(String[]::new);
    }

    public FeedItem[] getFeedsFromResponse() throws IOException {
        MyLogger.info("Filling an array of model classes FeedItem");
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(getResponseWithAllFeeds(), FeedItem[].class);
    }

    public String[] getAllFeedsNames() throws IOException {
        MyLogger.info("Getting an array of feeds names from the array of model classes FeedItem");
        FeedItem[] feedItems = getFeedsFromResponse();
        return Arrays.stream(feedItems).map(FeedItem::getName).toArray(String[]::new);
    }

    public String[] getAllFeedsId() throws IOException {
        MyLogger.info("Getting an array of feeds keys for the query from the array of model classes FeedItem");
        FeedItem[] feedItems = getFeedsFromResponse();
        return Arrays.stream(feedItems).map(FeedItem::getKey).toArray(String[]::new);
    }

    public String[] getFeedsNewsTitleItems(String feedId) throws IOException {
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithNewsByFeed(feedId));
        List<NewsItem> newsItems = latestNews.getData();
        MyLogger.info("Getting " + newsItems.size() + " feeds news titles from the response");
        //return a sorted array of titles and replace from them two and more spaces and no-break spaces
        return newsItems.stream().map(newsItem -> newsItem.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).sorted().toArray(String[]::new);
    }

    public String getResultCoinsForQuery(List<Coin> coins) {//build result string from coins list for request
        StringBuilder resultCoinsForQuery = new StringBuilder("");
        for (Coin c : coins) {
            resultCoinsForQuery.append(",").append(c.getAbbreviationCoin());
        }
        return resultCoinsForQuery.substring(1, resultCoinsForQuery.length());
    }

    public String getResultCurrenciesForQuery(List<Currency> currencies) {//build result string from currencies list for request
        StringBuilder resultCurrenciesForQuery = new StringBuilder("");
        for (Currency c : currencies) {
            resultCurrenciesForQuery.append(",").append(c.getNameOfCurrency());
        }
        return resultCurrenciesForQuery.substring(1, resultCurrenciesForQuery.length());
    }

    private Response getResponseWithMultiPrice(String coinAbbreviations, String currencyAbbreviations) {
        MyLogger.info("Getting response with multiprice");
        return RestAssured.given().queryParam("fsyms", coinAbbreviations).
                queryParam("tsyms", currencyAbbreviations).
                when().get(MULTIPRICE_RELATIVE_PATH).andReturn();
    }

    private MultiPrice getMultiPriceFromResponse(Response response) throws IOException {
        MyLogger.info("Filling MultiPrice model class");
        return ResponseUtils.getObjectFromResponse(response, MultiPrice.class);
    }

    public MultiPrice getCoinsMultiPriceByCurrency(String coinAbbreviations, String currencyAbbreviations) throws IOException {
        MyLogger.info("Getting prices for " + coinAbbreviations + " coins from the response in currencies " + currencyAbbreviations);
        return getMultiPriceFromResponse(getResponseWithMultiPrice(coinAbbreviations, currencyAbbreviations));
    }

}