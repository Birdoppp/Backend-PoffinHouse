package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.TypeEnum;
import lombok.Data;

@Data
public class PokemonOutputDto {
    private Long id;
    private String name;
    private int nationalDex;
    private TypeEnum.POKEMON_TYPE type;
}
