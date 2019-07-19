package com.epam.tat21.crypto.api.tests;

import static com.epam.tat21.crypto.api.data.JsonSchemasNames.ALL_COINLIST_SCHEMA;

import org.testng.Assert;
import org.testng.annotations.Test;

import com.epam.tat21.crypto.api.utils.JsonSchemaValidation;
import com.epam.tat21.crypto.ui.service.CommonConditions;
import com.epam.testng.JIRATestKey;

public class ValidateAllCoinlistJsonSchemaTest extends CommonConditions {
	
	@JIRATestKey(key = "EPMFARMATS-9619")
	@Test
	public void jsonSchemaValidationTest() {
		 Assert.assertTrue(JsonSchemaValidation.
	                validateJsonFromResponseBySchema(apiSteps.
	                        getResponseWithCoinsInfo(), ALL_COINLIST_SCHEMA));
	}
}
