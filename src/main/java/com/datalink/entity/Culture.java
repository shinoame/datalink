package com.datalink.entity;

import java.io.Serializable;

public class Culture implements Serializable {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String history;
    private String origin;

    public Culture(Integer id, String name, String description, String image, String history, String origin) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.image = image;
        this.history = history;
        this.origin = origin;
    }

    public Integer getId() {
        return id;
    }

    public Culture() {
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getHistory() {
        return history;
    }

    public void setHistory(String history) {
        this.history = history;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
// Getters and setters
}
