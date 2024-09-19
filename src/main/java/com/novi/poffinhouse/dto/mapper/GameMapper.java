// src/main/java/com/novi/poffinhouse/dto/mapper/GameMapper.java

package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.output.*;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.*;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class GameMapper {

    private final RegionMapRepository regionMapRepository;
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final TeamRepository teamRepository;
    private final BerryRepository berryRepository;
    private final TeamMapper teamMapper;

    public GameMapper(PokemonRepository pokemonRepository, OwnedPokemonRepository ownedPokemonRepository, BerryRepository berryRepository, RegionMapRepository regionMapRepository, UserRepository userRepository, TeamRepository teamRepository, TeamMapper teamMapper) {
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.berryRepository = berryRepository;
        this.regionMapRepository = regionMapRepository;
        this.userRepository = userRepository;
        this.teamRepository = teamRepository;
        this.teamMapper = teamMapper;
    }

    public Game toEntity(GameInputDto inputDto) {
        Game game = new Game();
        game.setVersionName(inputDto.getVersionName());
        game.setGeneration(inputDto.getGeneration());
        game.setDescription(inputDto.getDescription());

        // Set RegionMap
        RegionMap regionMap = regionMapRepository.findById(inputDto.getRegionMapId())
                .orElseThrow(() -> new IllegalArgumentException("RegionMap not found."));
        game.setRegionMap(regionMap);

        // Set Pokemon list
        List<Pokemon> pokemonList = inputDto.getPokemonIds().stream()
                .map(pokemonRepository::findById)
                .map(optionalPokemon -> optionalPokemon.orElseThrow(() -> new IllegalArgumentException("Pokemon not found.")))
                .collect(Collectors.toList());
        game.setPokemonList(pokemonList);

        // Set Berry list
        List<Berry> berryList = inputDto.getBerryIds().stream()
                .map(berryRepository::findById)
                .map(optionalBerry -> optionalBerry.orElseThrow(() -> new IllegalArgumentException("Berry not found.")))
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
        outputDto.setUser(new GameUserOutputDto(game.getUser().getId(), game.getUser().getUsername()));
        outputDto.setPokemonList(game.getPokemonList().stream()
                .map(pokemon -> new GamePokemonOutputDto(pokemon.getId(), pokemon.getNationalDex(), pokemon.getName(), pokemon.getType()))
                .collect(Collectors.toList()));
        outputDto.setOwnedPokemonList(game.getOwnedPokemonList().stream()
                .map(ownedPokemon -> new GameOwnedPokemonOutputDto(ownedPokemon.getId(), ownedPokemon.getPokemon().getName(), ownedPokemon.getNickname(), ownedPokemon.getNature()))
                .collect(Collectors.toList()));
        if (game.getTeam() != null) {
            outputDto.setTeam(teamMapper.toDto(game.getTeam()));
        }
        outputDto.setBerryList(game.getBerryList().stream()
                .map(berry -> new GameBerryOutputDto(berry.getId(), berry.getIndexNumber(), berry.getName()))
                .collect(Collectors.toList()));

        return outputDto;
    }
}