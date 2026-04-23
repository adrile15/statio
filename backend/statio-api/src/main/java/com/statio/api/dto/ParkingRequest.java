package com.statio.api.dto;

import java.util.List;

public class ParkingRequest {

    private String title;
    private String description;
    private String location;
    private String vehicleType;
    private double price;
    private Long userId;

    private List<String> images; // 🔥 AÑADIDO

    public String getTitle() { return title; }
    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public String getLocation() { return location; }
    public void setLocation(String location) { this.location = location; }

    public String getVehicleType() { return vehicleType; }
    public void setVehicleType(String vehicleType) { this.vehicleType = vehicleType; }

    public double getPrice() { return price; }
    public void setPrice(double price) { this.price = price; }

    public Long getUserId() { return userId; }
    public void setUserId(Long userId) { this.userId = userId; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }
}