package com.example.rkjc.news_app_2;

import java.util.Date;

public class NewsItem {
    private String url;
    private String author;
    private String title;
    private String description;
    private String date;

    public NewsItem(String author, String title, String description, String url,String date) {
        this.author=author;
        this.description="Description:"+description;
        this.title="Title:"+title;
        this.url=url;
        this.date="Date:"+date;
    }

    public String getAuthor() {
        return author;
    }

    public String getDate() {
        return date;
    }

    public String getDescription() {
        return description;
    }

    public String getTitle() {
        return title;
    }

    public String getUrl() {
        return url;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setDescription(String desc) {
        this.description = desc;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}

