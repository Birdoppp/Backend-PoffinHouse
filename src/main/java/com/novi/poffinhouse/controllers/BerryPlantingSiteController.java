package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.services.BerryPlantingSiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/berry-planting-sites")
public class BerryPlantingSiteController {

    private final BerryPlantingSiteService berryPlantingSiteService;

    public BerryPlantingSiteController(BerryPlantingSiteService berryPlantingSiteService) {
        this.berryPlantingSiteService = berryPlantingSiteService;
    }

    @PostMapping
    public ResponseEntity<BerryPlantingSiteOutputDto> createBerryPlantingSite(@Valid @RequestBody BerryPlantingSiteInputDto berryPlantingSiteInputDto) {
        return ResponseEntity.ok(berryPlantingSiteService.createBerryPlantingSite(berryPlantingSiteInputDto));
    }

    @GetMapping("/{id}")
    public ResponseEntity<BerryPlantingSiteOutputDto> getBerryPlantingSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(berryPlantingSiteService.getBerryPlantingSiteById(id));
    }

    @GetMapping
    public ResponseEntity<List<BerryPlantingSiteOutputDto>> getAllBerryPlantingSites() {
        return ResponseEntity.ok(berryPlantingSiteService.getAllBerryPlantingSites());
    }

    @PatchMapping("/{berryPlantingSiteId}/berries")
    public ResponseEntity<BerryPlantingSiteOutputDto> adjustBerriesInBerryPlantingSite(
            @PathVariable Long berryPlantingSiteId,
            @Valid @RequestBody Map<Integer, Long> berriesBySlots) {

        berryPlantingSiteService.adjustBerriesInBerryPlantingSite(berryPlantingSiteId, berriesBySlots);
        BerryPlantingSiteOutputDto site = berryPlantingSiteService.getBerryPlantingSiteById(berryPlantingSiteId);

        return ResponseEntity.ok(site);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteBerryPlantingSite(@RequestBody Long id) {
        berryPlantingSiteService.deleteBerryPlantingSite(id);
        return ResponseEntity.ok("Berry Planting Site " + id + " deleted successfully");
    }
}
