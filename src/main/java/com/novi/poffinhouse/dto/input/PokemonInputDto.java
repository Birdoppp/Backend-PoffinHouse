package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class PokemonInputDto {
    @NotNull(message = "Name cannot be null")
    private String name;

    @Positive(message = "National Dex number must be positive")
    private int nationalDex;

    @NotNull(message = "Type cannot be null")
    private TypeEnum.POKEMON_TYPE type;


    @Positive
    private Integer healthPoints;

    @Positive
    private Integer attack;

    @Positive
    private Integer defence;

    @Positive
    private Integer spAttack;

    @Positive
    private Integer spDefence;

    @Positive
    private Integer speed;
}
