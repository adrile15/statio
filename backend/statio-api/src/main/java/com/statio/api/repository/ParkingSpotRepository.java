package com.statio.api.repository;

import com.statio.api.model.ParkingSpot;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, Long> {

List<ParkingSpot> findByVehicleType(String vehicleType);

List<ParkingSpot> findByCity(String city);

}