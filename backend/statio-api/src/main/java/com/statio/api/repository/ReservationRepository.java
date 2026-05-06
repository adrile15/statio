package com.statio.api.repository;

import com.statio.api.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface ReservationRepository extends JpaRepository<Reservation, Long> {

    List<Reservation> findByUserId(Long userId);

    List<Reservation> findByParkingSpotId(Long parkingSpotId);

    Optional<Reservation> findByAccessCode(String accessCode);
}