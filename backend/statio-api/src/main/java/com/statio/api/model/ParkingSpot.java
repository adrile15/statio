package com.statio.api.model;

import jakarta.persistence.*;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class ParkingSpot {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // =========================
    // USER
    // =========================

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // =========================
    // INFO PARKING
    // =========================

    private String title;

    private String description;

    private String location;

    private String vehicleType;

    private double price;

    // coordenadas mapa
    private Double latitude;

    private Double longitude;

    // =========================
    // IMÁGENES
    // =========================

    @ElementCollection
    @CollectionTable(
        name = "parking_spot_images",
        joinColumns = @JoinColumn(name = "parking_spot_id")
    )
    @Column(name = "image_url")
    private List<String> images;

    // =========================
    // DISPONIBILIDAD
    // =========================

    @OneToMany(
        mappedBy = "parkingSpot",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    @JsonManagedReference
    private List<AvailabilitySlot> availabilitySlots;

    // =========================
    // RESERVAS
    // =========================

    @OneToMany(mappedBy = "parkingSpot")
    @JsonIgnore
    private List<Reservation> reservations;

    // =========================
    // CONSTRUCTOR
    // =========================

    public ParkingSpot() {}

    // =========================
    // GETTERS & SETTERS
    // =========================

    public Long getId() {
        return id;
    }

    // USER
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    // TITLE
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    // DESCRIPTION
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    // LOCATION
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    // VEHICLE
    public String getVehicleType() {
        return vehicleType;
    }

    public void setVehicleType(String vehicleType) {
        this.vehicleType = vehicleType;
    }

    // PRICE
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    // LATITUDE
    public Double getLatitude() {
        return latitude;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    // LONGITUDE
    public Double getLongitude() {
        return longitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    // IMAGES
    public List<String> getImages() {
        return images;
    }

    public void setImages(List<String> images) {
        this.images = images;
    }

    // AVAILABILITY
    public List<AvailabilitySlot> getAvailabilitySlots() {
        return availabilitySlots;
    }

    public void setAvailabilitySlots(
            List<AvailabilitySlot> availabilitySlots) {

        this.availabilitySlots = availabilitySlots;
    }

    // RESERVATIONS
    public List<Reservation> getReservations() {
        return reservations;
    }

    public void setReservations(
            List<Reservation> reservations) {

        this.reservations = reservations;
    }
}