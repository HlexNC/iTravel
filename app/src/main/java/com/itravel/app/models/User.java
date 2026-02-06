package com.itravel.app.models;

public class User {
    private String id;
    private String email;
    private String name;
    private String bio;
    // travelerType can be an enum or string
    private String travelerType;
    private String profileImageUrl;
    private boolean isVerified;

    public User() {
    }

    public User(String id, String email, String name, String travelerType) {
        this.id = id;
        this.email = email;
        this.name = name;
        this.travelerType = travelerType;
        this.isVerified = false;
    }

    // Getters and Setters
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getTravelerType() {
        return travelerType;
    }

    public void setTravelerType(String travelerType) {
        this.travelerType = travelerType;
    }

    public String getProfileImageUrl() {
        return profileImageUrl;
    }

    public void setProfileImageUrl(String profileImageUrl) {
        this.profileImageUrl = profileImageUrl;
    }

    public boolean isVerified() {
        return isVerified;
    }

    public void setVerified(boolean verified) {
        isVerified = verified;
    }
}
