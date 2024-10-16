package com.novi.poffinhouse.dto.output.game;

import lombok.Data;

@Data
public class TeamOwnedPokemonOutputDto {
    private Long id;
    private String pokemonName;
    private String nickname;
    private String nature;

    private Integer beauty;
    private Integer coolness;
    private Integer cuteness;
    private Integer cleverness;
    private Integer toughness;

}