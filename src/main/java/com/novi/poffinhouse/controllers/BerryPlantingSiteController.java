package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.services.BerryPlantingSiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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

    @GetMapping
    public ResponseEntity<List<BerryPlantingSiteOutputDto>> getAllBerryPlantingSites() {
        return ResponseEntity.ok(berryPlantingSiteService.getAllBerryPlantingSites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BerryPlantingSiteOutputDto> getBerryPlantingSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(berryPlantingSiteService.getBerryPlantingSiteById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteBerryPlantingSite(@PathVariable Long id) {
        berryPlantingSiteService.deleteBerryPlantingSite(id);
        return ResponseEntity.ok().build();
    }

    //    Assign Berry to BerryPlantingSite
    private String formatBerriesBySlots(Map<Integer, Long> berriesBySlots) {
        return berriesBySlots.entrySet().stream()
                .map(entry -> "Slot " + entry.getKey() + ": Berry ID " + entry.getValue())
                .collect(Collectors.joining(", "));
    }

    @PostMapping("/{berryPlantingSiteId}/berries")
    public ResponseEntity<String> plantBerriesInBerryPlantingSite(
            @PathVariable Long berryPlantingSiteId,
            @RequestBody Map<Integer, Long> berriesBySlots) {

        berryPlantingSiteService.plantBerriesInBerryPlantingSite(berryPlantingSiteId, berriesBySlots);
        String formattedBerries = formatBerriesBySlots(berriesBySlots);
        return ResponseEntity.ok("Berries planted successfully: " + formattedBerries);
    }


    @PatchMapping("/{berryPlantingSiteId}/berries")
    public ResponseEntity<String> adjustBerriesInBerryPlantingSite(
            @PathVariable Long berryPlantingSiteId,
            @RequestBody Map<Integer, Long> berriesBySlots) {

        berryPlantingSiteService.adjustBerriesInBerryPlantingSite(berryPlantingSiteId, berriesBySlots);
        String formattedBerries = formatBerriesBySlots(berriesBySlots);
        return ResponseEntity.ok("Berry Planting Site updated successfully. Changed Berries: " + formattedBerries);
    }


}
