package com.epam.tat21.crypto.api.model;

import com.epam.tat21.crypto.api.utils.NewsItemComparator;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LatestNews {

    @JsonProperty("Type")
    private Integer type;

    @JsonProperty("Message")
    private String message;

    @JsonProperty("Data")
    private List<NewsItem> data;

    public void setMessage(String message) {
        this.message = message;
    }

    public void setData(List<NewsItem> data) {
        this.data = data;
    }

    public String getMessage() {
        return message;
    }

    public List<NewsItem> getData() {
        return data;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public List<NewsItem> getSortedData() {
        data.sort(new NewsItemComparator());
        return data;
    }
}
