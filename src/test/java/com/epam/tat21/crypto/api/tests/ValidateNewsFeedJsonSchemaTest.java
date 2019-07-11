package com.epam.tat21.crypto.api.tests;

import com.epam.tat21.crypto.ui.tests.CommonConditions;
import com.epam.tat21.crypto.api.utils.JsonSchemaValidation;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import static com.epam.tat21.crypto.api.data.JsonSchemasNames.FEEDS_SCHEMA;

public class ValidateNewsFeedJsonSchemaTest extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9412")
    @Test()
    public void jsonSchemaValidationTest() {
        Assert.assertTrue(JsonSchemaValidation.
                validateJsonFromResponseBySchema(apiSteps.
                        getResponseWithAllFeeds(), FEEDS_SCHEMA));
    }
}
