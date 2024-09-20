package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.TypeEnum;
import lombok.Data;

@Data
public class PokemonOutputDto {
    private Long id;
    private String name;
    private int nationalDex;
    private TypeEnum.POKEMON_TYPE type;

    private Integer healthPoints;
    private Integer attack;
    private Integer defence;
    private Integer spAttack;
    private Integer spDefence;
    private Integer speed;
    private Boolean validated;
}
