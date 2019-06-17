package com.epam.tat21.crypto.api.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class NewsItem implements Serializable {
    private String id;
    private String guid;
    private Integer published_on;
    private String imageurl;
    private String title;
    private String url;
    private String source;
    private String body;
    private String tags;
    private String categories;
    private String upvotes;
    private String downvotes;
    private String lang;
    private NewsItemSourceInfo source_info;

    public void setId(String id) {
        this.id = id;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public void setPublished_on(Integer published_on) {
        this.published_on = published_on;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
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

    public Integer getPublished_on() {
        return published_on;
    }

    public String getImageurl() {
        return imageurl;
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

    public NewsItemSourceInfo getSource_info() {
        return source_info;
    }

    public void setSource_info(NewsItemSourceInfo source_info) {
        this.source_info = source_info;
    }
}
