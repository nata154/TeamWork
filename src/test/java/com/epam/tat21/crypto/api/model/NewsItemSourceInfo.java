package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsItemSourceInfo {

    private String name;
    private String lang;
    private String img;

    public String getName() {
        return name;
    }

    public String getLang() {
        return lang;
    }

    public String getImg() {
        return img;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public void setImg(String img) {
        this.img = img;
    }
}
