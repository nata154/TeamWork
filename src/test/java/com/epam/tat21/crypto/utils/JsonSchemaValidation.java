package com.epam.tat21.crypto.utils;

import com.epam.tat21.crypto.exceptions.JsonSchemaValidationException;
import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.File;
import java.io.IOException;
import java.net.URL;

public class JsonSchemaValidation {

    public static boolean validateJsonSchema(String jsonUrlPath, String jsonSchemaPath) {
        return validate(loadJsonObject(jsonUrlPath), loadJsonSchema(jsonSchemaPath));
    }

    public static boolean validateJsonSchemaLocal(String jsonUrlPath, String jsonSchemaPath) {
        return validate(loadJsonObjectLocal(jsonUrlPath), loadJsonSchema(jsonSchemaPath));
    }


    private static JsonNode loadJsonObjectLocal(String jsonUrlPath) {
        try {
            return JsonLoader.fromPath((new File(jsonUrlPath).getAbsolutePath()).replaceAll("//", File.separator));
        } catch (IOException e) {
            throw new JsonSchemaValidationException("Cannot load JSON object from current path");
        }
    }

    private static JsonNode loadJsonObject(String jsonUrlPath) {
        try {
            return JsonLoader.fromURL(new URL(jsonUrlPath));
        } catch (IOException e) {
            throw new JsonSchemaValidationException("Cannot load JSON object from URL");
        }
    }

    private static JsonNode loadJsonSchema(String jsonSchemaPath) {
        try {
            return JsonLoader.fromPath((new File(jsonSchemaPath).getAbsolutePath()).replaceAll("//", File.separator));
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
