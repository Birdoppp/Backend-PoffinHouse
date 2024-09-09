package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.mapper.PokemonMapper;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.Pokemon;
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

    @GetMapping("/id/{id}")
    public ResponseEntity<PokemonOutputDto> getPokemonById(@PathVariable Long id) {
        PokemonOutputDto pokemon = pokemonService.getPokemonById(id);
        return ResponseEntity.ok(pokemon);
    }

    @GetMapping("/nationalDex/{nationalDexNumber}")
    public ResponseEntity<PokemonOutputDto> getPokemonByNationalDex(@PathVariable int nationalDexNumber) {
        Pokemon pokemon = pokemonService.getPokemonByNationalDex(nationalDexNumber);
        return ResponseEntity.ok(PokemonMapper.toOutputDto(pokemon));
    }

    @GetMapping
    public ResponseEntity<List<PokemonOutputDto>> getAllPokemon() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllPokemon();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/validated")
    public ResponseEntity<List<PokemonOutputDto>> getAllValidatedPokemon() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllValidatedPokemonOrdered();
        return ResponseEntity.ok(pokemonList);
    }

    @GetMapping("/ordered")
    public ResponseEntity<List<PokemonOutputDto>> getAllPokemonOrdered() {
        List<PokemonOutputDto> pokemonList = pokemonService.getAllPokemonOrdered();
        return ResponseEntity.ok(pokemonList);
    }

    @PutMapping("/{id}")
    public ResponseEntity<PokemonOutputDto> updatePokemon(@PathVariable Long id, @RequestBody PokemonInputDto inputDto) {
        PokemonOutputDto updatedPokemon = pokemonService.updatePokemon(id, inputDto);
        return ResponseEntity.ok(updatedPokemon);
    }

    @PutMapping("/validate")
    public ResponseEntity<PokemonOutputDto> validatePokemon(@RequestBody int nationalDexId) {
        PokemonOutputDto validatedPokemon = pokemonService.validatePokemon(nationalDexId);
        return ResponseEntity.ok(validatedPokemon);
    }

    @DeleteMapping
    public ResponseEntity<String> deletePokemon(@RequestBody int nationalDexNumber) {
        Pokemon pokemon = pokemonService.getPokemonByNationalDex(nationalDexNumber);
        String pokemonName = pokemon.getName();
        pokemonService.deletePokemon(nationalDexNumber);

        return ResponseEntity.ok("Pokemon " + pokemonName + " with nationalDex number " + nationalDexNumber + " are deleted. These pokemon are deleted from games and owned pokemon lists.");
    }
}
