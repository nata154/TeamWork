package com.epam.tat21.crypto.bo;

public enum Countries {
    UK("United Kingdom"),
    RUSSIA("Russia"),
    DENMARK("Denmark"),
    JAPAN("Japan"),
    SINGAPORE("Singapore"),
    BRAZIL("Brazil");

    private String nameOfCountry;

    Countries(String nameCountry) {
        this.nameOfCountry = nameCountry;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }
}
