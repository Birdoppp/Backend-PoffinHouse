package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.dto.mapper.PokemonMapper;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.util.Capitalize;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class PokemonService {


    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository, OwnedPokemonRepository ownedPokemonRepository) {
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
    }

    public PokemonOutputDto createPokemon(@Valid PokemonInputDto inputDto) {
        if (pokemonRepository.existsByNameIgnoreCase(inputDto.getName())) {
            throw new IllegalArgumentException("A Pokémon with the name '" + Capitalize.getCapitalizedString(inputDto.getName()) + "' already exists.");
        }

        Pokemon pokemon = PokemonMapper.toEntity(inputDto);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return PokemonMapper.toOutputDto(savedPokemon);
    }

    public PokemonOutputDto getPokemonById(Long id) {
        Optional<Pokemon> optionalPokemon = pokemonRepository.findById(id);
        if (optionalPokemon.isPresent()) {
            return PokemonMapper.toOutputDto(optionalPokemon.get());
        }
        throw new IllegalArgumentException("Pokemon with id " + id + " not found.");
    }

    public Pokemon getPokemonByNationalDex(Long nationalDex) {
        return pokemonRepository.findByNationalDex(nationalDex).orElseThrow(() -> new IllegalArgumentException("Pokemon with nationalDex number " + nationalDex + " not found."));
    }

    public List<PokemonOutputDto> getAllPokemon() {
        return pokemonRepository.findAll().stream().map(PokemonMapper::toOutputDto).collect(Collectors.toList());
    }

    public List<PokemonOutputDto> getAllValidatedPokemonOrdered() {
        return pokemonRepository.findAllValidatedOrderedByNationalDex().stream().map(PokemonMapper::toOutputDto).collect(Collectors.toList());
    }

    public List<PokemonOutputDto> getAllUnvalidatedPokemonOrdered() {
        return pokemonRepository.findAllUnvalidatedOrderedByNationalDex().stream().map(PokemonMapper::toOutputDto).collect(Collectors.toList());
    }

    public PokemonOutputDto updatePokemon(Long nationalDex, @Valid PokemonInputDto inputDto) {
        Pokemon existingPokemon = pokemonRepository.findByNationalDex(nationalDex).orElseThrow(() -> new IllegalArgumentException("Pokemon with nationalDex " + nationalDex + " not found."));
        existingPokemon.setName(inputDto.getName());
        existingPokemon.setType(inputDto.getType());

        existingPokemon.setHealthPoints(inputDto.getHealthPoints());
        existingPokemon.setAttack(inputDto.getAttack());
        existingPokemon.setDefence(inputDto.getDefence());
        existingPokemon.setSpAttack(inputDto.getSpAttack());
        existingPokemon.setSpDefence(inputDto.getSpDefence());
        existingPokemon.setSpeed(inputDto.getSpeed());
        existingPokemon.setValidated(false);

        Pokemon updatedPokemon = pokemonRepository.save(existingPokemon);
        return PokemonMapper.toOutputDto(updatedPokemon);
    }

    public PokemonOutputDto validatePokemon(Long nationalDex) {
        Pokemon existingPokemon = pokemonRepository.findByNationalDex(nationalDex).orElseThrow(() -> new IllegalArgumentException("Pokemon with id " + nationalDex + " not found."));
        existingPokemon.setValidated(true);

        Pokemon validatedPokemon = pokemonRepository.save(existingPokemon);
        return PokemonMapper.toOutputDto(validatedPokemon);
    }

    public void deletePokemon(Long nationalDex) {
        Pokemon pokemon = pokemonRepository.findByNationalDex(nationalDex).orElseThrow(() -> new IllegalArgumentException("Pokemon with id " + nationalDex + " not found."));
        pokemon.getOwnedPokemonList().forEach(ownedPokemon -> ownedPokemon.getTeams().forEach(team -> team.getOwnedPokemon().remove(ownedPokemon)));
        ownedPokemonRepository.deleteAll(pokemon.getOwnedPokemonList());
        pokemon.getGames().forEach(game -> game.getPokemonList().remove(pokemon));

        pokemonRepository.deleteByNationalDex(nationalDex);

    }

}
