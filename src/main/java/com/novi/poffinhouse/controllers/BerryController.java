package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.services.BerryService;
import com.novi.poffinhouse.util.PreferencesEnum;
import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/berries")
public class BerryController {

    private final BerryService berryService;

    public BerryController(BerryService berryService) {
        this.berryService = berryService;
    }

    @PostMapping
    public ResponseEntity<BerryOutputDto> createBerry(@Valid @RequestBody BerryInputDto berryInputDto) {
        BerryOutputDto createdBerry = berryService.createBerry(berryInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBerry);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<BerryOutputDto> getBerryById(@PathVariable Long id) {
        BerryOutputDto berry = berryService.getBerryById(id);
        return ResponseEntity.ok(berry);
    }

    @GetMapping("/index-number/{indexNumber}")
    public ResponseEntity<BerryOutputDto> getBerryByIndexNumber(@PathVariable Long indexNumber) {
        BerryOutputDto berry = berryService.getBerryByIndexNumber(indexNumber);
        return ResponseEntity.ok(berry);
    }

    @GetMapping
    public ResponseEntity<List<BerryOutputDto>> getAllBerries() {
        List<BerryOutputDto> berries = berryService.getAllBerries();
        return ResponseEntity.ok(berries);
    }

    @GetMapping("/validated")
    public ResponseEntity<List<BerryOutputDto>> getAllValidatedBerriesOrderedByIndexNumber() {
        List<BerryOutputDto> berries = berryService.getAllValidatedBerriesOrderedByIndexNumber();
        return ResponseEntity.ok(berries);
    }

    @GetMapping("/unvalidated")
    public ResponseEntity<List<BerryOutputDto>> getUnvalidatedBerries() {
        List<BerryOutputDto> berries = berryService.getUnvalidatedBerries();
        return ResponseEntity.ok(berries);
    }

    @GetMapping("/types")
    public ResponseEntity<TypeEnum.BERRY_CATEGORY_TYPE[]> getAllTypes() {
        return ResponseEntity.ok(TypeEnum.BERRY_CATEGORY_TYPE.values());
    }

    @GetMapping("/flavors")
    public ResponseEntity<PreferencesEnum.FLAVOR[]> getAllFlavors() {
        return ResponseEntity.ok(PreferencesEnum.FLAVOR.values());
    }

    @PutMapping("/index-number/{indexNumber}")
    public ResponseEntity<BerryOutputDto> updateBerry(@PathVariable Long indexNumber, @Valid @RequestBody BerryInputDto berryInputDto) {
        BerryOutputDto updatedBerry = berryService.updateBerry(indexNumber, berryInputDto);
        return ResponseEntity.ok(updatedBerry);
    }

    @PatchMapping("/validate")
    public ResponseEntity<BerryOutputDto> validateBerries(@RequestBody Long indexNumber) {
        BerryOutputDto validateBerry = berryService.validateBerry(indexNumber);
        return ResponseEntity.ok(validateBerry);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteBerry(@RequestBody Long indexNumber) {
        String message = berryService.deleteBerry(indexNumber);
        return ResponseEntity.ok(message);
    }

}
