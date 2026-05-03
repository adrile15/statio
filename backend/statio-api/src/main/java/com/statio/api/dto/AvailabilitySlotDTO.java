package com.statio.api.dto;

import java.time.LocalDate;
import java.time.LocalTime;

public class AvailabilitySlotDTO {

    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;

    public LocalDate getDate() {
        return date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }
}