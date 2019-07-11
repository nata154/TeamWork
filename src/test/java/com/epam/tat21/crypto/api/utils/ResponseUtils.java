package com.epam.tat21.crypto.api.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;

import java.io.IOException;

public class ResponseUtils {

    private ResponseUtils() {
    }

    /**
     * A method below serves for filling model classes from response.
     * It receives Response object and a model class, which filling is needed,
     * then with jackson library reads the response, fills the model class
     * and after all returns it.
     */
    public static <T> T getObjectFromResponse(Response response, Class<T> tClass) throws IOException {
        return new ObjectMapper().readValue(response.getBody().asString(), tClass);
    }
}
