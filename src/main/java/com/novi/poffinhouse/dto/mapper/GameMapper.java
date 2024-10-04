package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.output.game.*;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.*;
import com.novi.poffinhouse.util.GameIdListSetter;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class GameMapper {

    private final RegionMapRepository regionMapRepository;
    private final GameIdListSetter gameIdListSetter;
    private final TeamMapper teamMapper;

    public GameMapper(
            RegionMapRepository regionMapRepository, GameIdListSetter gameIdListSetter, TeamMapper teamMapper) {
        this.regionMapRepository = regionMapRepository;
        this.gameIdListSetter = gameIdListSetter;
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

        // Set Pokémon list
        if (inputDto.getPokemonInput() != null) {
            game.setPokemonList(gameIdListSetter.PokemonListByGeneration(inputDto.getPokemonInput(), inputDto.getGeneration()));
        }
        // Set Berry list
        if (inputDto.getBerryInput() != null) {
            game.setBerryList(gameIdListSetter.BerryList(inputDto.getBerryInput()));
        }

        return game;
    }

    public GameOutputDto toDto(Game game) {
        GameOutputDto outputDto = new GameOutputDto();
        outputDto.setId(game.getId());
        outputDto.setVersionName(game.getVersionName());
        outputDto.setGeneration(game.getGeneration());
        outputDto.setDescription(game.getDescription());

        // Convert to GameOutputDto
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