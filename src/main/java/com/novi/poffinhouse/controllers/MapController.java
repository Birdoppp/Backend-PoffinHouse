package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/maps")
public class MapController {

    private final MapService mapService;

    @Autowired
    public MapController(MapService mapService) {
        this.mapService = mapService;
    }

    @PostMapping
    public ResponseEntity<MapOutputDto> createMap(@RequestBody MapInputDto mapInputDto) {
        MapOutputDto mapOutputDto = mapService.createMap(mapInputDto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(mapOutputDto.getId()).toUri();
        return ResponseEntity.created(uri).body(mapOutputDto);
    }

    @PostMapping("/{regionName}/locations")
    public ResponseEntity<Location> addLocationToMap(@PathVariable String regionName, @RequestBody Location location) {
        Location addedLocation = mapService.addLocationToMap(regionName, location);

        URI uri = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(addedLocation.getId())
                .toUri();
        return ResponseEntity.created(uri).body(addedLocation);
    }
    @GetMapping("/{regionName}")
    public MapOutputDto getMap(@PathVariable String regionName) {
        return mapService.getMap(regionName);
    }

    @GetMapping("/{regionName}/locations")
    public List<Location> getAllLocations(@PathVariable String regionName) {
        return mapService.getAllLocations(regionName);
    }
}
