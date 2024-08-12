package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.services.PokemonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemon")
public class PokemonController {

    private final PokemonService pokemonService;

    public PokemonController(PokemonService pokemonService) {
        this.pokemonService = pokemonService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<PokemonOutputDto> createPokemon(@RequestBody PokemonInputDto inputDto) {
        PokemonOutputDto newPokemon = pokemonService.createPokemon(inputDto);
        return ResponseEntity.ok(newPokemon);
    }

    @GetMapping("/{id}")
    public ResponseEntity<PokemonOutputDto> getPokemonById(@PathVariable Long id) {
        PokemonOutputDto pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping
    public ResponseEntity<List<PokemonOutputDto>> getAllPokemon() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllPokemon();
        return ResponseEntity.ok(pokemonList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonOutputDto> updatePokemon(@PathVariable Long id, @RequestBody PokemonInputDto inputDto) {
        PokemonOutputDto updatedPokemon = pokemonService.updatePokemon(id, inputDto);
        return ResponseEntity.ok(updatedPokemon);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePokemon(@PathVariable Long id) {
        pokemonService.deletePokemon(id);
        return ResponseEntity.noContent().build();
    }
}
