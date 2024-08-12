package com.novi.poffinhouse.dto.output;

import lombok.Data;

@Data
public class OwnedPokemonOutputDto {
    private Long id;
    private String pokemonName;
    private String nickname;
    private String nature;
    private String caughtByTrainerName;

    private Integer beauty;
    private Integer coolness;
    private Integer cuteness;
    private Integer cleverness;
    private Integer toughness;

}
