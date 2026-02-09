package com.itravel.app.models;

public class FeedItem {
    private String title;
    private String description;
    private String imageUrl;
    private String authorName;
    private int likes;

    public FeedItem(String title, String description, String imageUrl, String authorName, int likes) {
        this.title = title;
        this.description = description;
        this.imageUrl = imageUrl;
        this.authorName = authorName;
        this.likes = likes;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public String getAuthorName() {
        return authorName;
    }

    public int getLikes() {
        return likes;
    }

    private String weatherTemp;
    private String weatherIcon;

    public void setWeather(String temp, String icon) {
        this.weatherTemp = temp;
        this.weatherIcon = icon;
    }

    public String getWeatherTemp() {
        return weatherTemp;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }
}
