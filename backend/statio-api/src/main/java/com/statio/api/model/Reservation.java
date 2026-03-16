package com.statio.api.model;

import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Reservation {

@Id
@GeneratedValue(strategy = GenerationType.IDENTITY)
private Long id;

private String accessCode;

private String status;

private LocalDateTime createdAt;

@ManyToOne
@JoinColumn(name = "user_id")
private User user;

@ManyToOne
@JoinColumn(name = "parking_spot_id")
private ParkingSpot parkingSpot;

@ManyToOne
@JoinColumn(name = "slot_id")
private AvailabilitySlot slot;

public Reservation() {}

public Long getId() {
return id;
}

public String getAccessCode() {
return accessCode;
}

public void setAccessCode(String accessCode) {
this.accessCode = accessCode;
}

public String getStatus() {
return status;
}

public void setStatus(String status) {
this.status = status;
}

public LocalDateTime getCreatedAt() {
return createdAt;
}

public void setCreatedAt(LocalDateTime createdAt) {
this.createdAt = createdAt;
}

}