package com.epam.tat21.crypto.api.data;

public enum JsonSchemasNames {

    LATEST_NEWS_SCHEMA("latestNewsSchema.json"),
    FEEDS_SCHEMA("newsFeedJsonSchema.json"),
    MULTI_PRICE_SCHEMA("multiPriceJsonSchema.json");

    private String schemaName;

    JsonSchemasNames(String schemaName) {
        this.schemaName = schemaName;
    }

    public String getSchemaName() {
        return schemaName;
    }
}
