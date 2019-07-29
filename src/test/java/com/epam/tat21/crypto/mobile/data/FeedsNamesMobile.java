package com.epam.tat21.crypto.mobile.data;

public enum FeedsNamesMobile {
    CRYPTO_GLOBE("CryptoGlobe"),
    COIN_DESK("CoinDesk"),
    BITCOIN_MAGAZINE("Bitcoin Magazine"),
    CCN("CCN"),
    BITCOINIST("Bitcoinist");

    private String feedName;

    FeedsNamesMobile(String feedName) {
        this.feedName = feedName;
    }

    public String getFeedName() {
        return feedName;
    }
}
