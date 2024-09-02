package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;

import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.repositories.RegionMapRepository;
import com.novi.poffinhouse.services.LocationService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/locations")
public class LocationController {
    private final LocationService locationService;
    public final RegionMapRepository regionMapRepository;

    public LocationController(LocationService locationService, RegionMapRepository regionMapRepository) {
        this.locationService = locationService;
        this.regionMapRepository = regionMapRepository;
    }

    @PostMapping
    public ResponseEntity<LocationOutputDto> createLocation(@Valid @RequestBody LocationInputDto location) {
        Long regionMapId = location.getRegionMapId();
        if (regionMapId == null) {
            throw new IllegalArgumentException("RegionMapId is required");
        } else {
            regionMapRepository.findById(regionMapId).orElseThrow(() -> new IllegalArgumentException("RegionMap with id " + regionMapId + " not found"));
        }
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
//        LocationOutputDto existingLocation = locationService.getLocationById(id);
//        if (existingLocation == null) {
//            return ResponseEntity.notFound().build();
//        }
        Long regionMapId = updatedLocation.getRegionMapId();
        if (regionMapId == null) {
            throw new IllegalArgumentException("RegionMapId is required");
        } else {
            regionMapRepository.findById(regionMapId).orElseThrow(() -> new IllegalArgumentException("RegionMap with id " + regionMapId + " not found"));

            LocationOutputDto updatedLocationDto = locationService.updateLocation(id, updatedLocation);

            return ResponseEntity.ok(updatedLocationDto);
        }
    }

    @DeleteMapping
    public ResponseEntity<String> deleteLocation(@RequestBody Long id) {
        locationService.deleteLocation(id);
        return ResponseEntity.ok("Location " + id + " deleted successfully");
    }


}
