package com.epam.tat21.crypto.bo;

public enum Countries {
    CHINA("China"),
    UK("United Kingdom"),
    RUSSIA("Russia"),
    DENMARK("Denmark"),
    JAPAN("Japan"),
    SINGAPORE("Singapore"),
    BRAZIL("Brazil"),
    UNKNOWN("Unknown");

    private String nameOfCountry;

    Countries(String nameCountry) {
        this.nameOfCountry = nameCountry;
    }

    public String getNameOfCountry() {
        return nameOfCountry;
    }
}
