package com.statio.api.controller;

import com.statio.api.model.AvailabilitySlot;
import com.statio.api.repository.AvailabilitySlotRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/availability")
@CrossOrigin
public class AvailabilityController {

@Autowired
private AvailabilitySlotRepository slotRepository;

@GetMapping("/{parkingId}")
public List<AvailabilitySlot> getSlotsByParking(@PathVariable Long parkingId){
return slotRepository.findByParkingSpotId(parkingId);
}

@PostMapping
public AvailabilitySlot createSlot(@RequestBody AvailabilitySlot slot){
return slotRepository.save(slot);
}

}