package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.services.OwnedPokemonService;
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

            //    Post/Creation of OwnedPokemon happens in Game


    @GetMapping
    public ResponseEntity<List<OwnedPokemonOutputDto>> getAllOwnedPokemon() {
        List<OwnedPokemonOutputDto> ownedPokemonList = ownedPokemonService.getAllOwnedPokemon();
        return ResponseEntity.ok(ownedPokemonList);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnedPokemonOutputDto> getOwnedPokemonById(@PathVariable Long id) {
        OwnedPokemonOutputDto ownedPokemon = ownedPokemonService.getOwnedPokemonById(id);
        return ResponseEntity.ok(ownedPokemon);
    }

    @PutMapping("/{id}")
    public ResponseEntity<OwnedPokemonOutputDto> updateOwnedPokemon(@PathVariable Long id, @RequestBody OwnedPokemonInputDto inputDto) {
        OwnedPokemonOutputDto updatedOwnedPokemon = ownedPokemonService.updateOwnedPokemon(id, inputDto);
        return ResponseEntity.ok(updatedOwnedPokemon);
    }

    @DeleteMapping
    public ResponseEntity<String> deleteOwnedPokemon(@RequestBody Long id) {
        String message = ownedPokemonService.deleteOwnedPokemon(id);
        return ResponseEntity.ok(message);
    }
}
