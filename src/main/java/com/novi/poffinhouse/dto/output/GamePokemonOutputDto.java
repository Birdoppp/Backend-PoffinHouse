package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GamePokemonOutputDto {
    private Long id;
    private String name;
    private TypeEnum.POKEMON_TYPE type;

    public GamePokemonOutputDto(Long id, @NotBlank String name, TypeEnum.POKEMON_TYPE type) {
    }
}
