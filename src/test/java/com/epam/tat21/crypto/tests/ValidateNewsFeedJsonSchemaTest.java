package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.utils.JsonSchemaValidation;
import org.testng.Assert;
import org.testng.annotations.Test;

public class ValidateNewsFeedJsonSchemaTest {

    private static final String JSON_URL_FROM_API = "https://min-api.cryptocompare.com/data/news/feeds";
    private static final String SCHEMA_PATH = "src/test/resources/JsonSchemes/newsFeedJsonSchema.json";

    @Test()
    public void jsonSchemaValidationTest(){
        Assert.assertTrue(JsonSchemaValidation.validateJsonSchema(JSON_URL_FROM_API,SCHEMA_PATH));
    }
}
