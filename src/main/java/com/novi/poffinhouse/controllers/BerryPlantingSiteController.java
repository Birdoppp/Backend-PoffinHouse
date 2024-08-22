package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.services.BerryPlantingSiteService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @PostMapping("/{berryPlantingSiteId}/slots/{slotId}/berries")
    public ResponseEntity<Void> addBerryToSoilSlot(@PathVariable Long berryPlantingSiteId, @PathVariable Long slotId, @RequestBody Long berryId) {
        berryPlantingSiteService.plantBerryInSoilSlot(berryPlantingSiteId, slotId, berryId);
        return ResponseEntity.ok().build();
//
    }
}
