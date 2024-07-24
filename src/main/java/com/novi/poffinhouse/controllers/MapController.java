package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.services.MapService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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
    public MapOutputDto createMap(@RequestBody MapInputDto mapInputDto) {
        return mapService.createMap(mapInputDto);
    }

    @PostMapping("/{regionName}/locations")
    public void addLocationToMap(@PathVariable String regionName, @RequestBody LocationInputDto location) {
        mapService.addLocationToMap(regionName, location);
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
