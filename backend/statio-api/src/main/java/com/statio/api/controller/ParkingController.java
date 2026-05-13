package com.statio.api.controller;

import com.statio.api.dto.ParkingRequest;
import com.statio.api.dto.AvailabilitySlotDTO;
import com.statio.api.model.ParkingSpot;
import com.statio.api.model.User;
import com.statio.api.model.AvailabilitySlot;
import com.statio.api.repository.ParkingSpotRepository;
import com.statio.api.repository.UserRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.ArrayList;

@RestController
@RequestMapping("/api/parking")
@CrossOrigin
public class ParkingController {

    @Autowired
    private ParkingSpotRepository parkingRepo;

    @Autowired
    private UserRepository userRepo;

    // TODOS LOS PARKINGS
    @GetMapping
    public List<ParkingSpot> getAllParkings() {
        return parkingRepo.findAll();
    }

    // PARKING POR ID
    @GetMapping("/{id}")
    public ParkingSpot getParkingById(@PathVariable Long id) {
        return parkingRepo.findById(id).orElseThrow();
    }

    // PARKINGS DEL USUARIO
    @GetMapping("/user/{userId}")
    public List<ParkingSpot> getUserParkings(
            @PathVariable Long userId){

        return parkingRepo.findByUserId(userId);
    }

    // ELIMINAR PARKING
    @DeleteMapping("/{id}")
    public void deleteParking(@PathVariable Long id){

        parkingRepo.deleteById(id);
    }

    // EDITAR PARKING
    @PutMapping("/{id}")
    public ParkingSpot updateParking(
            @PathVariable Long id,
            @RequestBody ParkingRequest request){

        ParkingSpot parking =
            parkingRepo.findById(id).orElseThrow();

        parking.setTitle(request.getTitle());

        parking.setDescription(request.getDescription());

        parking.setLocation(request.getLocation());

        parking.setVehicleType(request.getVehicleType());

        parking.setPrice(request.getPrice());

        parking.setLatitude(request.getLatitude());

        parking.setLongitude(request.getLongitude());

        parking.setImages(request.getImages());

        // eliminar slots antiguos
        parking.getAvailabilitySlots().clear();

        List<AvailabilitySlot> newSlots = new ArrayList<>();

        if(request.getSlots() != null){

            for(AvailabilitySlotDTO dto : request.getSlots()){

                AvailabilitySlot slot = new AvailabilitySlot();

                slot.setDate(dto.getDate());

                slot.setStartTime(dto.getStartTime());

                slot.setEndTime(dto.getEndTime());

                slot.setAvailable(true);

                slot.setParkingSpot(parking);

                newSlots.add(slot);
            }
        }

        parking.getAvailabilitySlots().addAll(newSlots);

        return parkingRepo.save(parking);
    }

    // CREAR PARKING
    @PostMapping
    public ParkingSpot createParking(@RequestBody ParkingRequest request) {

        User user = userRepo.findById(request.getUserId()).orElseThrow();

        ParkingSpot parking = new ParkingSpot();

        parking.setTitle(request.getTitle());
        parking.setLocation(request.getLocation());
        parking.setVehicleType(request.getVehicleType());
        parking.setPrice(request.getPrice());
        parking.setDescription(request.getDescription());

        // coordenadas
        parking.setLatitude(request.getLatitude());
        parking.setLongitude(request.getLongitude());

        parking.setUser(user);

        parking.setImages(request.getImages());

        List<AvailabilitySlot> slots = new ArrayList<>();

        if (request.getSlots() != null) {

            for (AvailabilitySlotDTO dto : request.getSlots()) {

                AvailabilitySlot slot = new AvailabilitySlot();

                slot.setDate(dto.getDate());
                slot.setStartTime(dto.getStartTime());
                slot.setEndTime(dto.getEndTime());

                slot.setAvailable(true);

                slot.setParkingSpot(parking);

                slots.add(slot);
            }
        }

        parking.setAvailabilitySlots(slots);

        return parkingRepo.save(parking);
    }
}