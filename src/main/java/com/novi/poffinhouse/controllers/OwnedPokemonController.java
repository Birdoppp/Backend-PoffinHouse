package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.services.OwnedPokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/owned-pokemon")
public class OwnedPokemonController {

    private final OwnedPokemonService ownedPokemonService;

    public OwnedPokemonController(OwnedPokemonService ownedPokemonService) {
        this.ownedPokemonService = ownedPokemonService;
    }

    @PostMapping
    public ResponseEntity<OwnedPokemonOutputDto> createOwnedPokemon(@RequestBody OwnedPokemonInputDto inputDto) {
        OwnedPokemonOutputDto newOwnedPokemon = ownedPokemonService.createOwnedPokemon(inputDto);
        return new ResponseEntity<>(newOwnedPokemon, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnedPokemonOutputDto> getOwnedPokemonById(@PathVariable Long id) {
        OwnedPokemonOutputDto ownedPokemon = ownedPokemonService.getOwnedPokemonById(id);
        return ResponseEntity.ok(ownedPokemon);
    }

    @GetMapping
    public ResponseEntity<List<OwnedPokemonOutputDto>> getAllOwnedPokemon() {
        List<OwnedPokemonOutputDto> ownedPokemonList = ownedPokemonService.getAllOwnedPokemon();
        return ResponseEntity.ok(ownedPokemonList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnedPokemonOutputDto> updateOwnedPokemon(@PathVariable Long id, @RequestBody OwnedPokemonInputDto inputDto) {
        OwnedPokemonOutputDto updatedOwnedPokemon = ownedPokemonService.updateOwnedPokemon(id, inputDto);
        return ResponseEntity.ok(updatedOwnedPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwnedPokemon(@PathVariable Long id) {
        ownedPokemonService.deleteOwnedPokemon(id);
        return ResponseEntity.noContent().build();
    }
}
