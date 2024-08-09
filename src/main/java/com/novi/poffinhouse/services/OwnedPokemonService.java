package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnedPokemonService {

    private final OwnedPokemonRepository ownedPokemonRepository;
    private final PokemonRepository pokemonRepository;

    public OwnedPokemonService(OwnedPokemonRepository ownedPokemonRepository, PokemonRepository pokemonRepository) {
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public OwnedPokemonOutputDto createOwnedPokemon(OwnedPokemonInputDto inputDto) {
        Pokemon pokemon = pokemonRepository.findByName(inputDto.getPokemonName())
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with name " + inputDto.getPokemonName() + " not found."));

        OwnedPokemon ownedPokemon = new OwnedPokemon();
        ownedPokemon.setPokemon(pokemon);
        ownedPokemon.setNickname(inputDto.getNickname());
        ownedPokemon.setNature(inputDto.getNature());
        ownedPokemon.setCaughtByTrainerName(inputDto.getCaughtByTrainerName());

        OwnedPokemon savedOwnedPokemon = ownedPokemonRepository.save(ownedPokemon);
        return OwnedPokemonMapper.toOutputDto(savedOwnedPokemon);
    }


    public OwnedPokemonOutputDto getOwnedPokemonById(Long id) {
        Optional<OwnedPokemon> optionalOwnedPokemon = ownedPokemonRepository.findById(id);
        if (optionalOwnedPokemon.isPresent()) {
            return OwnedPokemonMapper.toOutputDto(optionalOwnedPokemon.get());
        }
        throw new IllegalArgumentException("OwnedPokemon with id " + id + " not found.");
    }

    public List<OwnedPokemonOutputDto> getAllOwnedPokemon() {
        return ownedPokemonRepository.findAll()
                .stream()
                .map(OwnedPokemonMapper::toOutputDto)
                .collect(Collectors.toList());
    }


    public OwnedPokemonOutputDto updateOwnedPokemon(Long id, OwnedPokemonInputDto inputDto) {
        OwnedPokemon existingOwnedPokemon = ownedPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with id " + id + " not found."));

        existingOwnedPokemon.setNickname(inputDto.getNickname());
        existingOwnedPokemon.setNature(inputDto.getNature());
        existingOwnedPokemon.setCaughtByTrainerName(inputDto.getCaughtByTrainerName());

        OwnedPokemon updatedOwnedPokemon = ownedPokemonRepository.save(existingOwnedPokemon);
        return OwnedPokemonMapper.toOutputDto(updatedOwnedPokemon);
    }

//    public String deleteOwnedPokemon(Long id) {
//        ownedPokemonRepository.deleteById(id);
//        return "OwnedPokemon with id " + id + " has been deleted.";
//    }

    public String deleteOwnedPokemon(Long id) {
        // Fetch the Pokémon entity by its ID
        Optional<OwnedPokemon> pokemonOptional = ownedPokemonRepository.findById(id);
        if (pokemonOptional.isPresent()) {
            OwnedPokemon pokemon = pokemonOptional.get();
            String pokemonName = pokemon.getPokemon().getName();
            // Delete the Pokémon from the repository
            ownedPokemonRepository.deleteById(id);
            // Return the response message with the Pokémon's name
            return "OwnedPokemon " + pokemonName + " with id " + id + " has been deleted.";
        } else {
            // Handle the case where the Pokémon with the given ID does not exist
            return "OwnedPokemon with id " + id + " not found.";
        }
    }


}
