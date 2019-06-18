package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinModel implements Serializable {

    public List<DataCoinModel> mapOfCoins;

    public CoinModel(List<DataCoinModel> mapOfCoins) {
        this.mapOfCoins = mapOfCoins;
    }

    public void setMapOfCoins(List<DataCoinModel> mapOfCoins) {
        this.mapOfCoins = mapOfCoins;
    }
}
