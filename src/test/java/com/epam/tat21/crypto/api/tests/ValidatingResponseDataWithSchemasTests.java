package com.epam.tat21.crypto.api.tests;

import com.epam.tat21.crypto.api.utils.JsonSchemaValidation;
import com.epam.tat21.crypto.ui.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.junit.Assert;
import org.testng.annotations.Test;

import static com.epam.tat21.crypto.api.data.JsonSchemasNames.LATEST_NEWS_SCHEMA;

public class ValidatingResponseDataWithSchemasTests extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9409")
    @Test
    public void validatingLatestNewsResponseBySchemaTest() {
        Assert.assertTrue(JsonSchemaValidation.validateJsonFromResponseBySchema(apiSteps.getResponseWithLatestNews(), LATEST_NEWS_SCHEMA));
    }

}
