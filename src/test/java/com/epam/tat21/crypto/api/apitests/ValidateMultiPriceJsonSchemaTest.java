package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.annotations.Test;

//import static com.epam.tat21.crypto.api.apidata.JsonSchemasNames.MULTI_PRICE_SCHEMA;

public class ValidateMultiPriceJsonSchemaTest  extends CommonConditions {
    @JIRATestKey(key = "EPMFARMATS-9517")
    @Test()
    public void jsonSchemaValidationTest() {
//        Assert.assertTrue(JsonSchemaValidation.
//                validateJsonFromResponseBySchema(apiSteps.
//                        getResponseWithAllFeeds(), MULTI_PRICE_SCHEMA));
    }
}
