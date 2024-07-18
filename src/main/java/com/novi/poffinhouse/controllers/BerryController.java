package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.exceptions.RecordNotFoundException;
import com.novi.poffinhouse.services.BerryService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;


import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/berries")
public class BerryController {
    private final BerryService berryService;

    public BerryController(BerryService berryService) {
        this.berryService = berryService;
    }

    // Mappings
    @GetMapping
    public ResponseEntity<List<BerryOutputDto>> getAllBerries() {
        return ResponseEntity.ok(berryService.getAllBerries());
    }

    @GetMapping("/{id}")
    public ResponseEntity<BerryOutputDto> getBerryById(@PathVariable int id) throws RecordNotFoundException {
        return ResponseEntity.ok(berryService.getBerryById(id));
    }

    @PostMapping
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<BerryOutputDto> createBerry(@RequestBody BerryInputDto berryInputDto, @AuthenticationPrincipal UserDetails userdetails) {
        BerryOutputDto berryOutputDto = berryService.createBerry(berryInputDto, berryInputDto.getIndexNumber());
        URI location = ServletUriComponentsBuilder.fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(berryOutputDto.getId())
                .toUri();
        return ResponseEntity.created(location).body(berryOutputDto);
    }

}
