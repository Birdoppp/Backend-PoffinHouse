package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.exceptions.BerryNotFoundException;
import com.novi.poffinhouse.services.BerryService;
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
    public ResponseEntity<BerryOutputDto> createBerry(@RequestBody BerryInputDto berryInputDto) {
        BerryOutputDto createdBerry = berryService.createBerry(berryInputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdBerry);
    }

    @GetMapping
    public ResponseEntity<List<BerryOutputDto>> getAllBerries() {
        List<BerryOutputDto> berries = berryService.getAllBerries();
        return ResponseEntity.ok(berries);
    }

    @GetMapping("/{id}")
    public ResponseEntity<BerryOutputDto> getBerryById(@PathVariable Long id) {
        BerryOutputDto berry = berryService.getBerryById(id);
        return ResponseEntity.ok(berry);
    }

    @PutMapping("/{id}")
    public ResponseEntity<BerryOutputDto> updateBerry(@PathVariable Long id, @Valid @RequestBody BerryInputDto berryInputDto) {
        BerryOutputDto updatedBerry = berryService.updateBerry(id, berryInputDto);
        return ResponseEntity.ok(updatedBerry);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteBerry(@PathVariable Long id) {
        String message = berryService.deleteBerry(id);
        return ResponseEntity.ok(message);
    }

    @ExceptionHandler(BerryNotFoundException.class)
    public ResponseEntity<String> handleBerryNotFoundException(BerryNotFoundException ex) {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ex.getMessage());
    }
}
