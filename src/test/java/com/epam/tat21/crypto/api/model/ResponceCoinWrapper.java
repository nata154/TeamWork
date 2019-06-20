package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponceCoinWrapper {

    @JsonProperty("Response")
    public String response;

    @JsonProperty("Message")
    public String message;

    @JsonProperty("Data")
    public CoinModel data;

    public ResponceCoinWrapper(String response, String message, CoinModel data) {
        this.response = response;
        this.message = message;
        this.data = data;
    }

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

    public CoinModel getData() {
        return data;
    }

    public void setData(CoinModel data) {
        this.data = data;
    }
}
