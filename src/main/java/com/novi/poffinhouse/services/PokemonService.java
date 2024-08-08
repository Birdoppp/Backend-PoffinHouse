package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.dto.mapper.PokemonMapper;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.PokemonRepository;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Service
@Validated
public class PokemonService {

    private final PokemonRepository pokemonRepository;
    private final PokemonMapper pokemonMapper;

    public PokemonService(PokemonRepository pokemonRepository, PokemonMapper pokemonMapper) {
        this.pokemonRepository = pokemonRepository;
        this.pokemonMapper = pokemonMapper;
    }

    public PokemonOutputDto savePokemon(@Valid PokemonInputDto inputDto) {
        Pokemon pokemon = pokemonMapper.toEntity(inputDto);
        Pokemon savedPokemon = pokemonRepository.save(pokemon);
        return pokemonMapper.toOutputDto(savedPokemon);
    }
}
