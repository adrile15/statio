package com.statio.api.dto;

import java.util.List;

public class ParkingRequest {

    private String title;
    private String description;
    private String location;
    private String vehicleType;
    private double price;
    private Long userId;

    // NUEVO
    private Double latitude;
    private Double longitude;

    private List<String> images;

    // slots enviados desde frontend
    private List<AvailabilitySlotDTO> slots;

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

    public List<AvailabilitySlotDTO> getSlots() { return slots; }
    public void setSlots(List<AvailabilitySlotDTO> slots) { this.slots = slots; }

    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }
}