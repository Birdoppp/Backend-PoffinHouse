package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.dto.mapper.PokemonMapper;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.PokemonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Validated
public class PokemonService {


    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public PokemonOutputDto createPokemon( @Valid PokemonInputDto inputDto) {
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

    public List<PokemonOutputDto> getAllPokemon() {
        return pokemonRepository.findAll()
                .stream()
                .map(PokemonMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public PokemonOutputDto updatePokemon(@Valid Long id, PokemonInputDto inputDto) {
        Pokemon existingPokemon = pokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with id " + id + " not found."));

        existingPokemon.setName(inputDto.getName());
        existingPokemon.setNationalDex(inputDto.getNationalDex());
        existingPokemon.setType(inputDto.getType());
        existingPokemon.setHealthPoints(inputDto.getHealthPoints());
        existingPokemon.setAttack(inputDto.getAttack());
        existingPokemon.setDefence(inputDto.getDefence());
        existingPokemon.setSpAttack(inputDto.getSpAttack());
        existingPokemon.setSpDefence(inputDto.getSpDefence());
        existingPokemon.setSpeed(inputDto.getSpeed());

        Pokemon updatedPokemon = pokemonRepository.save(existingPokemon);
        return PokemonMapper.toOutputDto(updatedPokemon);
    }

    public void deletePokemon(Long id) {
        pokemonRepository.deleteById(id);
    }
}
