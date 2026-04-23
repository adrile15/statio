package com.statio.api.controller;

import com.statio.api.dto.ParkingRequest;
import com.statio.api.model.ParkingSpot;
import com.statio.api.model.User;
import com.statio.api.repository.ParkingSpotRepository;
import com.statio.api.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin
public class ParkingController {

    @Autowired
    private ParkingSpotRepository parkingRepo;

    @Autowired
    private UserRepository userRepo;

    // 🔥 NUEVO: obtener todos los parkings
    @GetMapping
    public List<ParkingSpot> getAllParkings() {
        return parkingRepo.findAll();
    }

    // 🔥 crear parking (lo que ya tenías)
    @PostMapping
    public ParkingSpot createParking(@RequestBody ParkingRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow();

        ParkingSpot parking = new ParkingSpot();
        parking.setTitle(request.getTitle());
        parking.setLocation(request.getLocation());
        parking.setVehicleType(request.getVehicleType());
        parking.setPrice(request.getPrice());
        parking.setDescription(request.getDescription());
        parking.setUser(user);

        parking.setImages(request.getImages()); // 🔥 CLAVE

        return parkingRepo.save(parking);
    }

    @GetMapping("/{id}")
    public ParkingSpot getParkingById(@PathVariable Long id) {
        return parkingRepo.findById(id).orElseThrow();
    }
}