package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.output.game.GameMapOutputDto;
import com.novi.poffinhouse.dto.output.game.GameRegionMapOutputDto;
import com.novi.poffinhouse.models.game.gamemap.GameMap;

import java.util.stream.Collectors;

public class GameMapMapper {

    public static GameMapOutputDto toOutputDto(GameMap gameMap) {
        GameMapOutputDto gameMapOutputDto = new GameMapOutputDto();
        gameMapOutputDto.setId(gameMap.getId());
        gameMapOutputDto.setGameId(gameMap.getGame().getId());
        gameMapOutputDto.setRegionMap(new GameRegionMapOutputDto(gameMap.getRegionMap().getId(), gameMap.getRegionMap().getRegionName(), gameMap.getRegionMap().getRegionMapAtlas()));
        gameMapOutputDto.setLocationList(gameMap.getLocations().stream().map(LocationMapper::toOutputDtoShort).collect(Collectors.toList()));
        return gameMapOutputDto;
    }
}
