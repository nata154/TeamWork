package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.apiutils.ResponseUtils;
import com.epam.tat21.crypto.api.model.LatestNews;
import com.epam.tat21.crypto.api.model.NewsItem;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.List;
import java.util.stream.IntStream;

public class ApiSteps {

    public Response getResponseWithLatestNews() {
        RestAssured.baseURI = TestDataReader.getApiGetUrl() + "v2/news/";
        MyLogger.info("Getting response with latest news");
        return RestAssured.when().get().andReturn();
    }

    public LatestNews getLatestNewsFromResponse() throws IOException {
        MyLogger.info("Filling model classes LatestNews -> NewsItem -> NewsItemSourceInfo");
        Response response = getResponseWithLatestNews();
        //with Jackson library serialize a tree of model classes
        return ResponseUtils.getObjectFromResponse(response, LatestNews.class);
    }

    public String[] getLatestNewsTitleItems(int numberOfItems) throws IOException {
        MyLogger.info("Getting " + numberOfItems + " latest news titles from the response");
        LatestNews latestNews = getLatestNewsFromResponse();
        List<NewsItem> sortedNews = latestNews.getSortedData();
        //return subarray of titles, cause the news page can contain 50 news, while JSON can contain 100
        return IntStream.range(0, numberOfItems).mapToObj(i -> sortedNews.get(i).getTitle()).toArray(String[]::new);
    }
}
