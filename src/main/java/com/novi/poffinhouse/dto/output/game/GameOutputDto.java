package com.novi.poffinhouse.dto.output.game;


import com.novi.poffinhouse.dto.output.*;
import lombok.Data;

import java.util.List;

@Data
public class GameOutputDto {
    private Long id;
    private String versionName;
    private Integer generation;
    private String description;
    private GameRegionMapOutputDto regionMap;
    private GameUserOutputDto user;

    private List<GamePokemonOutputDto> pokemonList;
    private List<GameOwnedPokemonOutputDto> ownedPokemonList;
    private TeamOutputDto team;
    private List<GameBerryOutputDto> berryList;
}