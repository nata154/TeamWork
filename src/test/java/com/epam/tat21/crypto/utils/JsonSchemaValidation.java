package com.epam.tat21.crypto.utils;
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

    public static boolean validateJsonSchema(String jsonUrlPath, String jsonSchemaPath) throws IOException {
        return validate(loadJsonObject(jsonUrlPath), loadJsonSchema(jsonSchemaPath));
    }

    private static JsonNode loadJsonObject(String jsonUrlPath) throws IOException {
        return JsonLoader.fromURL(new URL(jsonUrlPath));
    }

    private static JsonNode loadJsonSchema(String jsonSchemaPath) throws IOException {
        return JsonLoader.fromPath((new File(jsonSchemaPath).getAbsolutePath()).replaceAll("//", File.separator));
    }

    private static boolean validate(JsonNode jsonObject, JsonNode jsonSchema) {
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema = null;
        try {
            schema = factory.getJsonSchema(jsonSchema);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
        ProcessingReport report = null;
        try {
            report = schema.validate(jsonObject);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }

        if (report.isSuccess()) {
            return true;
        } else {
            MyLogger.error(report.toString());
            return false;
        }
    }
}
