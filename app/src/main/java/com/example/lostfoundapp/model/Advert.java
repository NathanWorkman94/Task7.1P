package com.example.lostfoundapp.model;

public class Advert {

    private int id;
    private String name;
    private String phone;
    private String date;
    private String location;
    private String description;
    private String type;

    // No argument constructor
    public Advert() {
    }

    // Constructor
    public Advert(String name, String phone, String date, String location, String description, String type) {
        this.name = name;
        this.phone = phone;
        this.date = date;
        this.location = location;
        this.description = description;
        this.type = type;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
