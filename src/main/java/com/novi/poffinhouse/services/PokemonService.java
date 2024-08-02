package com.novi.poffinhouse.services;

import com.novi.poffinhouse.models.pokemon.Pokemon;

import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;



@Service
@Validated
public class PokemonService {

    private final PokemonRepository pokemonRepository;

    public PokemonService(PokemonRepository pokemonRepository) {
        this.pokemonRepository = pokemonRepository;
    }

    public Pokemon savePokemon(@Valid Pokemon pokemon) {
        validatePokemon(pokemon);
        return pokemonRepository.save(pokemon);
    }

    private void validatePokemon(Pokemon pokemon) {
        if (pokemon.getGeneration() < 6 && pokemon.getType() == TypeEnum.POKEMON_TYPE.FAIRY) {
            throw new IllegalArgumentException("Fairy type is not allowed for generation less than 6.");
        }

        if (pokemon.getGeneration() < 2 && (pokemon.getType() == TypeEnum.POKEMON_TYPE.DARK || pokemon.getType() == TypeEnum.POKEMON_TYPE.GHOST)) {
            throw new IllegalArgumentException("Dark or Ghost type is not allowed for generation less than 2.");
        }
    }
}
