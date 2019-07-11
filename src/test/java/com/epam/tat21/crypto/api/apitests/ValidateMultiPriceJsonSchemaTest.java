package com.epam.tat21.crypto.api.apitests;

import com.epam.tat21.crypto.api.apiutils.JsonSchemaValidation;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.Currency;
import com.epam.tat21.crypto.ui.tests.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.epam.tat21.crypto.api.apidata.JsonSchemasNames.MULTI_PRICE_SCHEMA;

public class ValidateMultiPriceJsonSchemaTest extends CommonConditions {

    @JIRATestKey(key = "EPMFARMATS-9517")
    @Test()
    public void jsonSchemaValidationTest() {
        List<Coin> coins = Arrays.asList(Coin.values());
        List<Currency> currencies = Arrays.asList(Currency.values());

        String coinAbbreviationsForRequest = apiSteps.getResultCoinsForQuery(coins);
        String currencyAbbreviationsForRequest = apiSteps.getResultCurrenciesForQuery(currencies);

        Assert.assertTrue(JsonSchemaValidation.
                validateJsonFromResponseBySchema(apiSteps.
                        getResponseWithMultiPrice(coinAbbreviationsForRequest, currencyAbbreviationsForRequest), MULTI_PRICE_SCHEMA));
    }
}