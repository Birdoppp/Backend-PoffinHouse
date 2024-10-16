package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.mapper.PokemonMapper;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.services.PokemonService;
import jakarta.validation.Valid;
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
    public ResponseEntity<PokemonOutputDto> createPokemon(@Valid @RequestBody PokemonInputDto inputDto) {
        PokemonOutputDto newPokemon = pokemonService.createPokemon(inputDto);
        return ResponseEntity.status(HttpStatus.CREATED).body(newPokemon);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<PokemonOutputDto> getPokemonById(@PathVariable Long id) {
        PokemonOutputDto pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/nationalDex/{nationalDex}")
    public ResponseEntity<PokemonOutputDto> getPokemonByNationalDex(@PathVariable Long nationalDex) {
        Pokemon pokemon = pokemonService.getPokemonByNationalDex(nationalDex);
        return ResponseEntity.ok(PokemonMapper.toOutputDto(pokemon));
    }

    @GetMapping
    public ResponseEntity<List<PokemonOutputDto>> getAllPokemon() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllPokemon();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/validated")
    public ResponseEntity<List<PokemonOutputDto>> getAllValidatedPokemonOrdered() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllValidatedPokemonOrdered();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/unvalidated")
    public ResponseEntity<List<PokemonOutputDto>> getAllUnvalidatedPokemon() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllUnvalidatedPokemonOrdered();
        return ResponseEntity.ok(pokemonList);
    }

    @PutMapping("/nationalDex/{nationalDex}")
    public ResponseEntity<PokemonOutputDto> updatePokemon(@PathVariable Long nationalDex, @Valid @RequestBody PokemonInputDto inputDto) {
        PokemonOutputDto updatedPokemon = pokemonService.updatePokemon(nationalDex, inputDto);
        return ResponseEntity.ok(updatedPokemon);
    }

    @PatchMapping("/validate")
    public ResponseEntity<PokemonOutputDto> validatePokemon(@RequestBody Long nationalDex) {
        PokemonOutputDto validatedPokemon = pokemonService.validatePokemon(nationalDex);
        return ResponseEntity.ok(validatedPokemon);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePokemon(@RequestBody Long nationalDex) {
        Pokemon pokemon = pokemonService.getPokemonByNationalDex(nationalDex);
        String pokemonName = pokemon.getName();
        pokemonService.deletePokemon(nationalDex);

        return ResponseEntity.ok("Pokemon " + pokemonName + " with nationalDex number " + nationalDex + " are deleted. These pokemon are deleted from games and owned pokemon lists.");
    }
}
