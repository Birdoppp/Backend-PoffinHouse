package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.OwnedPokemonContestConditionInputDto;
import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.services.OwnedPokemonService;
import jakarta.validation.Valid;
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
    public ResponseEntity<OwnedPokemonOutputDto> createOwnedPokemon(@Valid @RequestBody OwnedPokemonInputDto ownedPokemonInputDto) {
        OwnedPokemonOutputDto ownedPokemon = ownedPokemonService.createOwnedPokemon(ownedPokemonInputDto);
        return ResponseEntity.ok(ownedPokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnedPokemonOutputDto> getOwnedPokemonById(@PathVariable Long id) {
        OwnedPokemonOutputDto ownedPokemon = ownedPokemonService.getOwnedPokemonById(id);
        return ResponseEntity.ok(ownedPokemon);
    }

    @GetMapping("/game/{gameId}")
    public ResponseEntity<List<OwnedPokemonOutputDto>> getAllOwnedPokemonByGameId(@PathVariable Long gameId) {
        List<OwnedPokemonOutputDto> ownedPokemonList = ownedPokemonService.getAllOwnedPokemonByGameId(gameId);
        return ResponseEntity.ok(ownedPokemonList);
    }

    @GetMapping
    public ResponseEntity<List<OwnedPokemonOutputDto>> getAllOwnedPokemon() {
        List<OwnedPokemonOutputDto> ownedPokemonList = ownedPokemonService.getAllOwnedPokemon();
        return ResponseEntity.ok(ownedPokemonList);
    }

    @PatchMapping("/{id}/contest-condition")
    public ResponseEntity<OwnedPokemonOutputDto> adjustContestCondition(@PathVariable Long id, @Valid @RequestBody OwnedPokemonContestConditionInputDto contestCondition) {
        OwnedPokemonOutputDto updatedOwnedPokemon = ownedPokemonService.adjustContestCondition(id, contestCondition);
        return ResponseEntity.ok(updatedOwnedPokemon);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOwnedPokemon(@RequestBody Long id) {
        String message = ownedPokemonService.deleteOwnedPokemon(id);
        return ResponseEntity.ok(message);
    }
}
