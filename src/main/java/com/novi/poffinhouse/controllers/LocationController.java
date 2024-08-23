package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;

import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @PostMapping
    public ResponseEntity<LocationOutputDto> createLocation(@Valid @RequestBody LocationInputDto location) {
        LocationOutputDto createdLocation = locationService.createLocation(location);
        return ResponseEntity.ok(createdLocation);
    }

    @GetMapping
    public ResponseEntity<List<LocationOutputDto>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationOutputDto> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationOutputDto> updateLocation(@PathVariable Long id, @Valid @RequestBody LocationInputDto updatedLocation) {
        LocationOutputDto updatedLocationDto = locationService.updateLocation(id, updatedLocation);
        return ResponseEntity.ok(updatedLocationDto);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok().build();
    }


}
