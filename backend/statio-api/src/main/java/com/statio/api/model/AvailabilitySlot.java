package com.statio.api.model;

import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Entity
public class AvailabilitySlot {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private LocalDate date;

private LocalTime startTime;

private LocalTime endTime;

private boolean available;

@ManyToOne
@JoinColumn(name = "parking_spot_id")
private ParkingSpot parkingSpot;

public AvailabilitySlot() {}

public Long getId() {
return id;
}

public LocalDate getDate() {
return date;
}

public void setDate(LocalDate date) {
this.date = date;
}

public LocalTime getStartTime() {
return startTime;
}

public void setStartTime(LocalTime startTime) {
this.startTime = startTime;
}

public LocalTime getEndTime() {
return endTime;
}

public void setEndTime(LocalTime endTime) {
this.endTime = endTime;
}

public boolean isAvailable() {
return available;
}

public void setAvailable(boolean available) {
this.available = available;
}

}