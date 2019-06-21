package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.apiutils.ResponseUtils;
import com.epam.tat21.crypto.api.model.FeedItem;
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

    private static final String NEWS_RELATIVE_PATH = "v2/news/";
    private static final String FEEDS_RELATIVE_PATH = "news/feeds";

    public ApiSteps() {
        RestAssured.baseURI = TestDataReader.getApiGetUrl();
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

    public Response getResponseWithFeeds() {
        MyLogger.info("Getting response with feeds");
        return RestAssured.when().get(FEEDS_RELATIVE_PATH).andReturn();
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

    private FeedItem[] getFeedsFromResponse() throws IOException {
        MyLogger.info("Filling an array of model classes FeedItem");
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(getResponseWithFeeds(), FeedItem[].class);
    }

    public String[] getFeedsNames() throws IOException {
        MyLogger.info("Getting an array of feeds names from the array of model classes FeedItem");
        FeedItem[] feedItems = getFeedsFromResponse();
        return IntStream.range(0, feedItems.length).mapToObj(i -> feedItems[i].getName()).toArray(String[]::new);
    }
}
