package com.statio.api.controller;

import com.statio.api.model.Reservation;
import com.statio.api.repository.ReservationRepository;
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

@PostMapping
public Reservation createReservation(@RequestBody Reservation reservation){

reservation.setAccessCode(generateAccessCode());
reservation.setCreatedAt(LocalDateTime.now());
reservation.setStatus("CONFIRMED");

return reservationRepository.save(reservation);
}

@GetMapping("/user/{userId}")
public List<Reservation> getUserReservations(@PathVariable Long userId){
return reservationRepository.findByUserId(userId);
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