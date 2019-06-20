package com.epam.tat21.crypto.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.github.fge.jackson.JsonLoader;
import com.github.fge.jsonschema.core.exceptions.ProcessingException;
import com.github.fge.jsonschema.core.report.ProcessingReport;
import com.github.fge.jsonschema.main.JsonSchema;
import com.github.fge.jsonschema.main.JsonSchemaFactory;

import java.io.IOException;
import java.net.URL;

public class JsonSchemaValidation {

    public static boolean validateJsonSchema(String jsonUrlPath, String jsonSchemaPath) throws IOException {

        URL jsonURL = new URL(jsonUrlPath);
        final JsonNode incomingJson = JsonLoader.fromURL(jsonURL);
        final JsonNode jsonSchema = JsonLoader.fromPath(jsonSchemaPath);
        final JsonSchemaFactory factory = JsonSchemaFactory.byDefault();
        JsonSchema schema=null;
        try {
            schema = factory.getJsonSchema(jsonSchema);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }
        ProcessingReport report=null;
        try {
            report = schema.validate(incomingJson);
        } catch (ProcessingException e) {
            e.printStackTrace();
        }

        if (report.isSuccess()) {
            return true;
        } else return false;
    }
}
