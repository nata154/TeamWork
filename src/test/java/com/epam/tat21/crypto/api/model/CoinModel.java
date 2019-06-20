package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CoinModel implements Serializable {

    public List<DataCoinModel> listOfCoins;

    public CoinModel(List<DataCoinModel> listOfCoins) {
        this.listOfCoins = listOfCoins;
    }

    public void setMapOfCoins(List<DataCoinModel> listOfCoins) {
        this.listOfCoins = listOfCoins;
    }
}
