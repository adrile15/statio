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

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    private String title;
    private String description;
    private String location;
    private String vehicleType;
    private double price;

    @ElementCollection
    @CollectionTable(name = "parking_spot_images", joinColumns = @JoinColumn(name = "parking_spot_id"))
    @Column(name = "image_url")
    private List<String> images;

    //MOSTRAR EN FRONTEND
    @OneToMany(mappedBy = "parkingSpot", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<AvailabilitySlot> availabilitySlots;

    //NO NECESARIO EN FRONTEND
    @OneToMany(mappedBy = "parkingSpot")
    @JsonIgnore
    private List<Reservation> reservations;

    public ParkingSpot() {}

    public Long getId() { return id; }

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

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public List<String> getImages() { return images; }
    public void setImages(List<String> images) { this.images = images; }

    public List<AvailabilitySlot> getAvailabilitySlots() { return availabilitySlots; }
    public void setAvailabilitySlots(List<AvailabilitySlot> availabilitySlots) { this.availabilitySlots = availabilitySlots; }

    public List<Reservation> getReservations() { return reservations; }
    public void setReservations(List<Reservation> reservations) { this.reservations = reservations; }
}