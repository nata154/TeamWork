package com.epam.tat21.crypto.api.apisteps;

import com.epam.tat21.crypto.api.model.LatestNews;
import com.epam.tat21.crypto.service.TestDataReader;
import com.epam.tat21.crypto.utils.MyLogger;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.RestAssured;
import io.restassured.response.Response;

import java.io.IOException;
import java.util.stream.IntStream;

public class ApiSteps {

    private Response response;
    private LatestNews latestNews;

    public Response getResponseWithLatestNews() {
        RestAssured.baseURI = TestDataReader.getApiGetUrl() + "v2/news/";
        MyLogger.info("Getting response with latest news");
        response = RestAssured.when().get().andReturn();
        return response;
    }

    public LatestNews getLatestNewsFromResponse() throws IOException {
        MyLogger.info("Filling model classes LatestNews -> NewsItem -> NewsItemSourceInfo");
        //with Jackson library serialize a tree of model classes
        latestNews = new ObjectMapper().readValue(response.getBody().asString(), LatestNews.class);
        return latestNews;
    }

    public String[] get50LatestNewsTitleItems() {
        MyLogger.info("Getting 50 latest news titles from the response");
        //return subarray of 50 titles, cause the news page contains 50 news, while JSON can contain 100
        return IntStream.range(0, 50).mapToObj(i -> latestNews.getData().get(i).getTitle()).toArray(String[]::new);
    }
}
