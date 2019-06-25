package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsItem {

    private String id;
    private String guid;
    @JsonProperty("published_on")
    private Integer publishedOn;
    @JsonProperty("imageurl")
    private String imageUrl;
    private String title;
    private String url;
    private String source;
    private String body;
    private String tags;
    private String categories;
    private String upvotes;
    private String downvotes;
    private String lang;
    @JsonProperty("source_info")
    private NewsItemSourceInfo sourceInfo;

    public void setId(String id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setPublishedOn(Integer publishedOn) {
        this.publishedOn = publishedOn;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public void setCategories(String categories) {
        this.categories = categories;
    }

    public void setUpvotes(String upvotes) {
        this.upvotes = upvotes;
    }

    public void setDownvotes(String downvotes) {
        this.downvotes = downvotes;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }

    public String getId() {
        return id;
    }

    public String getGuid() {
        return guid;
    }

    public Integer getPublishedOn() {
        return publishedOn;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public String getSource() {
        return source;
    }

    public String getBody() {
        return body;
    }

    public String getTags() {
        return tags;
    }

    public String getCategories() {
        return categories;
    }

    public String getUpvotes() {
        return upvotes;
    }

    public String getDownvotes() {
        return downvotes;
    }

    public String getLang() {
        return lang;
    }

    public NewsItemSourceInfo getSourceInfo() {
        return sourceInfo;
    }

    public void setSourceInfo(NewsItemSourceInfo sourceInfo) {
        this.sourceInfo = sourceInfo;
    }
}
