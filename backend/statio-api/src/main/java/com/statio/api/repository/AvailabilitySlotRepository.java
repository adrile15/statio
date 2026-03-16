package com.statio.api.repository;

import com.statio.api.model.AvailabilitySlot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface AvailabilitySlotRepository extends JpaRepository<AvailabilitySlot, Long> {

List<AvailabilitySlot> findByParkingSpotId(Long parkingSpotId);

}