package com.datalink.entity;

import org.springframework.web.multipart.MultipartFile;

public class Food {
    private Integer id;
    private String name;
    private String description;
    private String image;
    private String specialty;
    private String origin;


    public Food() {
    }

    public Food(String name, String description, String image, String specialty, String origin) {
        this.name = name;
        this.description = description;
        this.image = image;
        this.specialty = specialty;
        this.origin = origin;
    }

    @Override
    public String toString() {
        return "Food{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", image='" + image + '\'' +
                ", specialty='" + specialty + '\'' +
                ", origin='" + origin + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
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

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public String getOrigin() {
        return origin;
    }

    public void setOrigin(String origin) {
        this.origin = origin;
    }
}
