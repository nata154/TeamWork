package com.epam.tat21.crypto.api.apiutils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;

public class ObjectCreatorFromResponse {

    private ObjectCreatorFromResponse() {
    }

    public static <T> T getObjectFromResponse(Response response, Class<T> tClass) throws IOException {
        return new ObjectMapper().readValue(response.getBody().asString(), tClass);
    }
}
