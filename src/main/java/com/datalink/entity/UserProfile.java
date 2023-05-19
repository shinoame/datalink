package com.datalink.entity;

public class UserProfile {
    private int id;
    private String username;
    private String avatar;
    private String fullName;
    private String bio;
    private String location;
    private String email;
    private String phoneNumber;

    // 省略getter和setter方法



    public UserProfile() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public UserProfile(int id, String username, String avatar, String fullName, String bio, String location, String email, String phoneNumber) {
        this.id = id;
        this.username = username;
        this.avatar = avatar;
        this.fullName = fullName;
        this.bio = bio;
        this.location = location;
        this.email = email;
        this.phoneNumber = phoneNumber;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getBio() {
        return bio;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }
}
