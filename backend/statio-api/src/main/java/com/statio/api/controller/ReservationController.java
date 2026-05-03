package com.statio.api.controller;

import com.statio.api.dto.ReservationRequest;
import com.statio.api.model.Reservation;
import com.statio.api.model.User;
import com.statio.api.model.ParkingSpot;
import com.statio.api.model.AvailabilitySlot;
import com.statio.api.repository.ReservationRepository;
import com.statio.api.repository.UserRepository;
import com.statio.api.repository.ParkingSpotRepository;
import com.statio.api.repository.AvailabilitySlotRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api/reservations")
@CrossOrigin
public class ReservationController {

    @Autowired
    private ReservationRepository reservationRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ParkingSpotRepository parkingRepository;

    @Autowired
    private AvailabilitySlotRepository slotRepository;

    @PostMapping
    public Reservation createReservation(@RequestBody ReservationRequest request) {

        User user = userRepository.findById(request.getUserId()).orElseThrow();
        ParkingSpot parking = parkingRepository.findById(request.getParkingSpotId()).orElseThrow();
        AvailabilitySlot slot = slotRepository.findById(request.getSlotId()).orElseThrow();

        if(!slot.isAvailable()){
            throw new RuntimeException("Slot no disponible");
        }

        Reservation reservation = new Reservation();
        reservation.setUser(user);
        reservation.setParkingSpot(parking);
        reservation.setSlot(slot);

        reservation.setAccessCode(generateAccessCode());
        reservation.setCreatedAt(LocalDateTime.now());
        reservation.setStatus("CONFIRMED");

        // bloquear slot
        slot.setAvailable(false);
        slotRepository.save(slot);

        return reservationRepository.save(reservation);
    }

    @GetMapping("/user/{userId}")
    public List<Reservation> getUserReservations(@PathVariable Long userId){
        return reservationRepository.findByUserId(userId);
    }

    //CANCELAR RESERVA
    @DeleteMapping("/{id}")
    public void cancelReservation(@PathVariable Long id){

        Reservation reservation = reservationRepository.findById(id).orElseThrow();

        // liberar slot
        AvailabilitySlot slot = reservation.getSlot();
        if(slot != null){
            slot.setAvailable(true);
            slotRepository.save(slot);
        }

        // eliminar reserva
        reservationRepository.deleteById(id);
    }

    private String generateAccessCode(){
        String chars = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        Random random = new Random();
        StringBuilder code = new StringBuilder();

        for(int i=0;i<6;i++){
            code.append(chars.charAt(random.nextInt(chars.length())));
        }

        return code.toString();
    }
}