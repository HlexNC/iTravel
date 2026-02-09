package com.itravel.app.models;

import com.google.gson.annotations.SerializedName;

public class UnsplashPhotoDto {
    @SerializedName("id")
    public String id;

    @SerializedName("urls")
    public Urls urls;

    @SerializedName("user")
    public User user;

    @SerializedName("description")
    public String description;

    @SerializedName("alt_description")
    public String altDescription;

    @SerializedName("location")
    public Location location;

    public static class Urls {
        @SerializedName("regular")
        public String regular;
        @SerializedName("small")
        public String small;
    }

    public static class User {
        @SerializedName("name")
        public String name;
        @SerializedName("username")
        public String username;
    }

    public static class Location {
        @SerializedName("name")
        public String name;
        @SerializedName("country")
        public String country;
        @SerializedName("position")
        public Position position;
    }

    public static class Position {
        @SerializedName("latitude")
        public Double latitude;
        @SerializedName("longitude")
        public Double longitude;
    }
}
