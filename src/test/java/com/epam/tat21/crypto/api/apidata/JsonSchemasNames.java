package com.epam.tat21.crypto.api.apidata;

public enum JsonSchemasNames {

    LATEST_NEWS_SCHEMA("latestNewsSchema.json"),
    FEEDS_SCHEMA("newsFeedJsonSchema.json");

    private String schemaName;

    JsonSchemasNames(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return schemaName;
    }
}