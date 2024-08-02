package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
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
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLocation.getId()).toUri();
        return ResponseEntity.created(uri).body(createdLocation);
    }
    @GetMapping
    public ResponseEntity<List<LocationOutputDto>> getAllLocations() {
        List<LocationOutputDto> locations = locationService.getAllLocations();
        return ResponseEntity.ok(locations);
    }
    @GetMapping("/{id}")
    public ResponseEntity<LocationOutputDto> getLocationById(@PathVariable Long id) {
       LocationOutputDto location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @PutMapping("/{id}")
    public ResponseEntity<LocationOutputDto> updateLocation(@PathVariable Long id, @RequestBody LocationInputDto inputDto) {
        LocationOutputDto updatedLocation = locationService.updateLocation(id, inputDto);
        return ResponseEntity.ok(updatedLocation);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteLocation(@PathVariable Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.noContent().build();
    }



}
