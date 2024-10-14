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

    @GetMapping
    public ResponseEntity<List<BerryPlantingSiteOutputDto>> getAllBerryPlantingSites() {
        return ResponseEntity.ok(berryPlantingSiteService.getAllBerryPlantingSites());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BerryPlantingSiteOutputDto> getBerryPlantingSiteById(@PathVariable Long id) {
        return ResponseEntity.ok(berryPlantingSiteService.getBerryPlantingSiteById(id));
    }


    //    Assign Berries to BerryPlantingSite soilSlots
//
//    @PostMapping("/{berryPlantingSiteId}/berries")
//    public ResponseEntity<String> plantBerriesInBerryPlantingSite(
//            @PathVariable Long berryPlantingSiteId,
//            @RequestBody Map<Integer, Long> berriesBySlots) {
//
//        berryPlantingSiteService.plantBerriesInBerryPlantingSite(berryPlantingSiteId, berriesBySlots);
//        BerryPlantingSiteOutputDto site = berryPlantingSiteService.getBerryPlantingSiteById(berryPlantingSiteId);
//        String formattedBerries = formatBerriesBySlots(site);
//        return ResponseEntity.ok("Berries planted successfully: " + formattedBerries);
//    }

    @PatchMapping("/{berryPlantingSiteId}/berries")
    public ResponseEntity<String> adjustBerriesInBerryPlantingSite(
            @PathVariable Long berryPlantingSiteId,
            @RequestBody Map<Integer, Long> berriesBySlots) {

        berryPlantingSiteService.adjustBerriesInBerryPlantingSite(berryPlantingSiteId, berriesBySlots);
        BerryPlantingSiteOutputDto site = berryPlantingSiteService.getBerryPlantingSiteById(berryPlantingSiteId);

        String formattedBerries = formatBerriesBySlots(site);
        return ResponseEntity.ok("Berry Planting Site updated successfully. " + formattedBerries);
    }

    private String formatBerriesBySlots(BerryPlantingSiteOutputDto site) {
        StringBuilder result = new StringBuilder();

        for (int i = 1; i <= site.getSoilSlots(); i++) {
            Long berry = site.getPlantedBerriesBySlots().get(i);
            if (berry != null) {
                result.append("Slot ").append(i).append(": BerryId ").append(berry).append(", ");
            } else {
                result.append("Slot ").append(i).append(": Empty, ");
            }
        }

        if (!result.isEmpty()) {
            result.setLength(result.length() - 2);
        }

        return result.toString();
    }


    @DeleteMapping
    public ResponseEntity<String> deleteBerryPlantingSite(@RequestBody Long id) {
        berryPlantingSiteService.deleteBerryPlantingSite(id);
        return ResponseEntity.ok("Berry Planting Site " + id + " deleted successfully");
    }
}
