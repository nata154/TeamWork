package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponceCoinWrapper {

    @JsonProperty("Response")
    public String response;

    @JsonProperty("Message")
    public String message;

    @JsonProperty("Data")
    public Map<String, DataCoinModel> data;

    public String getResponse() {
        return response;
    }

    public void setResponse(String response) {
        this.response = response;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Map<String, DataCoinModel> getData() {
        return data;
    }

    public void setData(Map<String, DataCoinModel> data) {
        this.data = data;
    }
}
