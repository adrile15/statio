package com.statio.api.controller;

import com.statio.api.model.ParkingSpot;
import com.statio.api.repository.ParkingSpotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/parkings")
@CrossOrigin
public class ParkingController {

@Autowired
private ParkingSpotRepository parkingRepository;

@GetMapping
public List<ParkingSpot> getAllParkings(){
return parkingRepository.findAll();
}

@GetMapping("/{id}")
public ParkingSpot getParkingById(@PathVariable Long id){
return parkingRepository.findById(id).orElse(null);
}

@PostMapping
public ParkingSpot createParking(@RequestBody ParkingSpot parking){
return parkingRepository.save(parking);
}

@PutMapping("/{id}")
public ParkingSpot updateParking(@PathVariable Long id, @RequestBody ParkingSpot updated){

ParkingSpot parking = parkingRepository.findById(id).orElseThrow();

parking.setTitle(updated.getTitle());
parking.setDescription(updated.getDescription());
parking.setCity(updated.getCity());
parking.setAddress(updated.getAddress());
parking.setVehicleType(updated.getVehicleType());
parking.setPricePerHour(updated.getPricePerHour());

return parkingRepository.save(parking);
}

@DeleteMapping("/{id}")
public void deleteParking(@PathVariable Long id){
parkingRepository.deleteById(id);
}

}