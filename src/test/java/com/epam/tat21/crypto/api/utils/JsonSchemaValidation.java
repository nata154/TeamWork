package com.epam.tat21.crypto.api.utils;

import com.epam.tat21.crypto.api.data.JsonSchemasNames;
import com.epam.tat21.crypto.exceptions.JsonSchemaValidationException;
import com.epam.tat21.crypto.ui.service.TestDataReader;
import com.epam.tat21.crypto.ui.utils.MyLogger;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;
import io.restassured.response.Response;

import java.io.File;
import java.io.IOException;
import java.net.URL;


public class JsonSchemaValidation {
    private JsonSchemaValidation() {
    }
    public static boolean validateJsonFromUrlBySchema(String jsonUrlPath, JsonSchemasNames jsonSchemaName) {
        return validate(loadJsonObjectFromUrl(jsonUrlPath), loadJsonSchema(jsonSchemaName.getSchemaName()));
    }
    public static boolean validateJsonFromResponseBySchema(Response response, JsonSchemasNames jsonSchemaName) {
        return validate(loadJsonObjectFromResponse(response), loadJsonSchema(jsonSchemaName.getSchemaName()));
    }

    private static JsonNode loadJsonObjectFromUrl(String jsonUrlPath) {
        MyLogger.info("Loading JSON from URL: " + jsonUrlPath);
        try {
            return JsonLoader.fromURL(new URL(jsonUrlPath));
        } catch (IOException e) {
            throw new JsonSchemaValidationException("Cannot load JSON object from URL");
        }
    }
    private static JsonNode loadJsonObjectFromResponse(Response response) {
        MyLogger.info("Loading JSON from the string which was gotten from the response");
        String json = response.getBody().asString();
        try {
            return JsonLoader.fromString(json);
        } catch (IOException e) {
            throw new JsonSchemaValidationException("Cannot load JSON object from the following string: " + json);
        }
    }
    private static JsonNode loadJsonSchema(String jsonSchemaName) {
        String schemaPath = TestDataReader.getJsonSchemasLocation() + jsonSchemaName;
        MyLogger.info("Loading the JSON schema " + jsonSchemaName + " from the path: " + schemaPath);
        try {
            return JsonLoader.fromPath((new File(schemaPath).getAbsolutePath()).replaceAll("//", File.separator));
        } catch (IOException e) {
            throw new JsonSchemaValidationException("Cannot load JSON Schema from current path");
        }
    }
    private static boolean validate(JsonNode jsonObject, JsonNode jsonSchema) {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema;
        try {
            schema = factory.getJsonSchema(jsonSchema);
        } catch (ProcessingException e) {
            throw new JsonSchemaValidationException("Entity of JSON Schema cannot be initialized from JsonSchemaFactory entity");
        }
        ProcessingReport report;
        try {
            report = schema.validate(jsonObject);
        } catch (ProcessingException e) {
            throw new JsonSchemaValidationException("Error objects validation");
        }
        if (report.isSuccess()) {
            return true;
        } else {
            MyLogger.error(report.toString());
            return false;
        }
    }
}



