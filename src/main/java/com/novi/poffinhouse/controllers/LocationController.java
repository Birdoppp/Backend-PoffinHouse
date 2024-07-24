package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.services.LocationService;
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
    public ResponseEntity<LocationOutputDto> createLocation(@RequestBody LocationInputDto inputDto) {
      LocationOutputDto createdLocation = locationService.createLocation(inputDto);
         URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(createdLocation.getId())
                .toUri();
        return ResponseEntity.created(uri).body(createdLocation);
    }

    @GetMapping("/{id}")
    public ResponseEntity<LocationOutputDto> getLocationById(@PathVariable int id) {
        com.novi.poffinhouse.dto.output.LocationOutputDto location = locationService.getLocationById(id);
        return ResponseEntity.ok(location);
    }

    @GetMapping
    public ResponseEntity<List<LocationOutputDto>> getAllLocations() {
        List<com.novi.poffinhouse.dto.output.LocationOutputDto> locations = locationService.getAllLocations();
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
