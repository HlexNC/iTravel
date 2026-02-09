package com.itravel.app.model;

public class Review {
    private long id;
    private long locationId;
    private long userId;
    private float rating;
    private String text;
    private String imageUris;
    private long timestamp;

    public Review() {
    }

    public Review(long locationId, long userId, float rating, String text, long timestamp) {
        this.locationId = locationId;
        this.userId = userId;
        this.rating = rating;
        this.text = text;
        this.timestamp = timestamp;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getLocationId() {
        return locationId;
    }

    public void setLocationId(long locationId) {
        this.locationId = locationId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public float getRating() {
        return rating;
    }

    public void setRating(float rating) {
        this.rating = rating;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getImageUris() {
        return imageUris;
    }

    public void setImageUris(String imageUris) {
        this.imageUris = imageUris;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
