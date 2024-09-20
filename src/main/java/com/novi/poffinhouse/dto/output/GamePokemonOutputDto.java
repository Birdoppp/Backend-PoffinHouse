package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GamePokemonOutputDto {
    private Long id;
    private int nationalDex;
    private String name;
    private TypeEnum.POKEMON_TYPE type;

    public GamePokemonOutputDto(Long id, int nationalDex, @NotBlank String name, TypeEnum.POKEMON_TYPE type) {
        this.id = id;
        this.nationalDex = nationalDex;
        this.name = name;
        this.type = type;
    }
}