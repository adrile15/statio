package com.statio.api.dto;

public class ReservationRequest {

    private Long userId;
    private Long parkingSpotId;
    private Long slotId;

    public Long getUserId() { return userId; }
    public Long getParkingSpotId() { return parkingSpotId; }
    public Long getSlotId() { return slotId; }
}