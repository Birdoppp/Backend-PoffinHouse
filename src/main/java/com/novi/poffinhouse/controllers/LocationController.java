package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.services.LocationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/locations")
public class LocationController {

    @Autowired
    private LocationService locationService;

    @PostMapping
    public ResponseEntity<LocationOutputDto> createLocation(@RequestBody LocationInputDto inputDto) {
        LocationOutputDto createdLocation = locationService.createLocation(inputDto);
        return ResponseEntity.ok(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationOutputDto> getLocationById(@PathVariable int id) {
        LocationOutputDto location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<LocationOutputDto>> getAllLocations() {
        List<LocationOutputDto> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationOutputDto> updateLocation(@PathVariable int id, @RequestBody LocationInputDto inputDto) {
        LocationOutputDto updatedLocation = locationService.updateLocation(id, inputDto);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable int id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }
}
