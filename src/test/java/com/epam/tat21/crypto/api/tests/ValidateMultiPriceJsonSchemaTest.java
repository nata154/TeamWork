package com.epam.tat21.crypto.api.tests;

import com.epam.tat21.crypto.api.utils.JsonSchemaValidation;
import com.epam.tat21.crypto.ui.businessObjects.Coin;
import com.epam.tat21.crypto.ui.businessObjects.Currency;
import com.epam.tat21.crypto.ui.service.CommonConditions;
import com.epam.testng.JIRATestKey;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.Arrays;
import java.util.List;

import static com.epam.tat21.crypto.api.data.JsonSchemasNames.MULTI_PRICE_SCHEMA;

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