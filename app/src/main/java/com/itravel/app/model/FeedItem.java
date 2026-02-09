package com.itravel.app.model;

public class FeedItem {
    private String title;
    private String description;
    private String imageUrl;
    private String authorName;
    private int likes;
    private double latitude;
    private double longitude;
    private String weatherTemp;
    private String weatherIcon;

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

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

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
