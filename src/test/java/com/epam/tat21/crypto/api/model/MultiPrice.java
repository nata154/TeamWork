package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonAnySetter;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * This model class is for the response with different coins and their worth in different currencies. For example:
 * {
 * "42": {
 * "USD": 28797.47,
 * "EUR": 25284.36
 * }
 * }
 */

public class MultiPrice {
    Map<String, Map<String, Double>> prices = new LinkedHashMap<>();

    public Map<String, Map<String, Double>> getPrices() {
        return prices;
    }

    public void setPrices(Map<String, Map<String, Double>> prices) {
        this.prices = prices;
    }

    @JsonAnySetter
    public void setPrices(String key, Map<String, Double> value) {
        prices.put(key, value);
    }
}
