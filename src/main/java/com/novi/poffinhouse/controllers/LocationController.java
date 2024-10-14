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
    public ResponseEntity<LocationOutputDto> createLocation(@Valid @RequestBody LocationInputDto inputDto) {
        LocationOutputDto createdLocation = locationService.createLocation(inputDto);
        return ResponseEntity.ok(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationOutputDto> getLocationById(@PathVariable Long id) {
        return ResponseEntity.ok(locationService.getLocationById(id));
    }

    @GetMapping
    public ResponseEntity<List<LocationOutputDto>> getAllLocations() {
        return ResponseEntity.ok(locationService.getAllLocations());
    }

    @PatchMapping("/{id}")
    public ResponseEntity<LocationOutputDto> adjustLocation(@PathVariable Long id, @Valid @RequestBody LocationInputDto locationInputDto) {
            LocationOutputDto updatedLocationDto = locationService.adjustLocation(id, locationInputDto);
            return ResponseEntity.ok(updatedLocationDto);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(@RequestBody Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Location " + id + " deleted successfully");
    }

}
