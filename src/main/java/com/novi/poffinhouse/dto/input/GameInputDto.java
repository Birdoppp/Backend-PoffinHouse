package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class GameInputDto {
    private String versionName;
    private int generation;
    private String description;
    private Long regionMapId;
    private List<Long> pokemonIds;
//    private List<Long> ownedPokemonIds;
    private List<Long> berryIds;
}