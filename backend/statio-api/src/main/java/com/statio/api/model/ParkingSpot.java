package com.statio.api.model;

import jakarta.persistence.*;
import java.util.List;

@Entity
public class ParkingSpot {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String title;

private String description;

private String city;

private String address;

private String vehicleType;

private double width;

private double length;

private double height;

private double pricePerHour;

@ManyToOne
@JoinColumn(name = "owner_id")
private User owner;

@OneToMany(mappedBy = "parkingSpot")
private List<AvailabilitySlot> availabilitySlots;

@OneToMany(mappedBy = "parkingSpot")
private List<Reservation> reservations;

public ParkingSpot() {}

public Long getId() {
return id;
}

public String getTitle() {
return title;
}

public void setTitle(String title) {
this.title = title;
}

public String getDescription() {
return description;
}

public void setDescription(String description) {
this.description = description;
}

public String getCity() {
return city;
}

public void setCity(String city) {
this.city = city;
}

public String getAddress() {
return address;
}

public void setAddress(String address) {
this.address = address;
}

public String getVehicleType() {
return vehicleType;
}

public void setVehicleType(String vehicleType) {
this.vehicleType = vehicleType;
}

public double getPricePerHour() {
return pricePerHour;
}

public void setPricePerHour(double pricePerHour) {
this.pricePerHour = pricePerHour;
}

}