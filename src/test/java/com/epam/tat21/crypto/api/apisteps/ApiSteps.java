package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.apiutils.ResponseUtils;
import com.epam.tat21.crypto.api.model.*;
import com.epam.tat21.crypto.bo.Coin;
import com.epam.tat21.crypto.bo.Currency;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import static com.epam.tat21.crypto.service.GlobalConstants.REGEX_FOR_SPACES;

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
        List<NewsItem> sortedNews = latestNews.getSortedData();
        MyLogger.info("Getting " + sortedNews.size() + " latest news titles from the response");
        //return an array of titles and replace from them two and more spaces and no-break spaces
        return sortedNews.stream().map(sortedNew -> sortedNew.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).toArray(String[]::new);
    }

    public String[] getCoinNewsTitleItems(Coin coin) throws IOException {
        LatestNews latestNews = getLatestNewsFromResponse(getResponseWithNewsByCoin(coin));
        List<NewsItem> sortedNews = latestNews.getSortedData();
        MyLogger.info("Getting " + sortedNews.size() + " coin news titles from the response");
        //return an array of titles and replace from them two and more spaces and no-break spaces
        return sortedNews.stream().map(sortedNew -> sortedNew.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).toArray(String[]::new);
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
        //return an array of titles and replace from them two and more spaces and no-break spaces
        return newsItems.stream().map(newsItem -> newsItem.getTitle().replaceAll(REGEX_FOR_SPACES, " ")).toArray(String[]::new);
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

    public MultiPrice getMultiPriceResponseAsArray(String coinAbbreviations, String currencyAbbreviations) throws IOException {
        MyLogger.info("Getting prices for coins from the response");
        return getMultiPriceFromResponse(getResponseWithMultiPrice(coinAbbreviations, currencyAbbreviations));
    }

    public boolean compareMultiPricesWithDelta(Map<String, Map<String, Double>> multiPricesFromPageAsArray, Map<String, Map<String, Double>> multiPriceResponseAsArray, List<Coin> coins, List<Currency> currencies, double deltaExpected) {
        boolean resultCompareMaps = true;
        for (int i = 0; i < coins.size(); i++) {
            for (int j = 0; j < currencies.size(); j++) {
                double coinCostInCurrencyFromPage = multiPricesFromPageAsArray.get(coins.get(i).getAbbreviationCoin()).get(currencies.get(j).getNameOfCurrency());
                double coinCostInCurrencyFromResponse = multiPriceResponseAsArray.get(coins.get(i).getAbbreviationCoin()).get(currencies.get(j).getNameOfCurrency());
                double deltaActual = ((Math.abs(coinCostInCurrencyFromPage - coinCostInCurrencyFromResponse)) / coinCostInCurrencyFromPage) * 100;

                BigDecimal bigDecimal = new BigDecimal(deltaActual);
                BigDecimal deltaActualRounded = bigDecimal.setScale(4, BigDecimal.ROUND_HALF_UP);

                MyLogger.info("Difference between current and expected values for " + coins.get(i).getAbbreviationCoin() + " in currency " + currencies.get(j).getNameOfCurrency() + " is: " + deltaActualRounded + ".");
                if (deltaActual > deltaExpected) {
                    MyLogger.error("For " + coins.get(i).getAbbreviationCoin() + " in currency " + currencies.get(j).getNameOfCurrency() + " difference between current and expected values is: " + deltaActualRounded + ". But expected no more than " + deltaExpected + " %.");
                    resultCompareMaps = false;
                }
            }
        }
        return resultCompareMaps;
    }
}