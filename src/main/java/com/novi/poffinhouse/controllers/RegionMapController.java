package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.services.RegionMapService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/region-maps")
public class RegionMapController {
    private final RegionMapService regionMapService;

    public RegionMapController(RegionMapService regionMapService) {
        this.regionMapService = regionMapService;
    }

    @PostMapping
    public ResponseEntity<RegionMapOutputDto> createMap(@Valid @RequestBody RegionMapInputDto inputDto) {
        return new ResponseEntity<>(regionMapService.createRegionMap(inputDto), HttpStatus.CREATED);
    }

    @GetMapping
    public ResponseEntity<List<RegionMapOutputDto>> getAllRegionMaps() {
        return ResponseEntity.ok(regionMapService.getAllRegionMaps());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RegionMap> getRegionMapById(@PathVariable Long id) {
        return new ResponseEntity<>(regionMapService.getRegionMapById(id), HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<RegionMap> updateMap(@PathVariable Long id,@Valid @RequestBody RegionMap regionMap) {
        return new ResponseEntity<>(regionMapService.updateRegionMap(id, regionMap), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteMap(@PathVariable Long id) {
        regionMapService.deleteRegionMap(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

//    @PostMapping("/{regionMapId}/locations")
//    public ResponseEntity<Void> addLocationToMap(@PathVariable Long regionMapId, @RequestBody Location location) {
//        regionMapService.addLocationToMap(regionMapId, location);
//        return new ResponseEntity<>(HttpStatus.CREATED);
//    }
}
