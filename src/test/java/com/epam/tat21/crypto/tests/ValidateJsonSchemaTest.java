package com.epam.tat21.crypto.tests;

import com.epam.tat21.crypto.utils.JsonSchemaValidation;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.io.IOException;

public class ValidateJsonSchemaTest {

    private static final String JSON_URL_FROM_API = "http://jsonplaceholder.typicode.com/users";
    private static final String SCHEMA_PATH = "jsonSchema.json";

    @Test()
    public void jsonSchemaValidationTest() throws IOException {
        Assert.assertTrue(JsonSchemaValidation.validateJsonSchema(JSON_URL_FROM_API,SCHEMA_PATH));
    }
}


