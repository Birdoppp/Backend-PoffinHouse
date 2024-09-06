package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.output.*;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.BerryRepository;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.repositories.RegionMapRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final BerryRepository berryRepository;
    private final RegionMapRepository regionMapRepository;

    public GameMapper(PokemonRepository pokemonRepository, OwnedPokemonRepository ownedPokemonRepository, BerryRepository berryRepository, RegionMapRepository regionMapRepository) {
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.berryRepository = berryRepository;
        this.regionMapRepository = regionMapRepository;
    }

    public Game toEntity(GameInputDto inputDto) {
        Game game = new Game();
        game.setVersionName(inputDto.getVersionName());
        game.setGeneration(inputDto.getGeneration());
        game.setDescription(inputDto.getDescription());

        // Set RegionMap
        RegionMap regionMap = regionMapRepository.findById(inputDto.getRegionMapId())
                .orElseThrow(() -> new IllegalArgumentException("RegionMap with id " + inputDto.getRegionMapId() + " not found."));
        game.setRegionMap(regionMap);

        // Set Pokemon list
        List<Pokemon> pokemonList = inputDto.getPokemonIds().stream()
                .map(id -> pokemonRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Pokemon with id " + id + " not found.")))
                .collect(Collectors.toList());
        game.setPokemonList(pokemonList);

        // Set OwnedPokemon list
        List<OwnedPokemon> ownedPokemonList = inputDto.getOwnedPokemonIds().stream()
                .map(id -> ownedPokemonRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with id " + id + " not found.")))
                .collect(Collectors.toList());
        game.setOwnedPokemonList(ownedPokemonList);

        // Set Berry list
        List<Berry> berryList = inputDto.getBerryIds().stream()
                .map(id -> berryRepository.findById(id)
                        .orElseThrow(() -> new IllegalArgumentException("Berry with id " + id + " not found.")))
                .collect(Collectors.toList());
        game.setBerryList(berryList);

        return game;
    }

    public GameOutputDto toDto(Game game) {
        GameOutputDto outputDto = new GameOutputDto();
        outputDto.setId(game.getId());
        outputDto.setVersionName(game.getVersionName());
        outputDto.setGeneration(game.getGeneration());
        outputDto.setDescription(game.getDescription());

        // Convert related entities to output DTOs
        outputDto.setRegionMap(new GameRegionMapOutputDto(game.getRegionMap().getId(), game.getRegionMap().getRegionName()));
        outputDto.setPokemonList(game.getPokemonList().stream()
                .map(pokemon -> new GamePokemonOutputDto(pokemon.getId(), pokemon.getName(), pokemon.getType()))
                .collect(Collectors.toList()));
        outputDto.setOwnedPokemonList(game.getOwnedPokemonList().stream()
                .map(ownedPokemon -> new GameOwnedPokemonOutputDto(ownedPokemon.getId(), ownedPokemon.getNickname(), ownedPokemon.getNature()))
                .collect(Collectors.toList()));
        outputDto.setBerryList(game.getBerryList().stream()
                .map(berry -> new GameBerryOutputDto(berry.getId(), berry.getName()))
                .collect(Collectors.toList()));

        return outputDto;
    }
}