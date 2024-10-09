package com.novi.poffinhouse.dto.output.game;

import com.novi.poffinhouse.util.TypeEnum;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class GamePokemonOutputDto {
    private Long id;
    private Long nationalDex;
    private String name;
    private TypeEnum.POKEMON_TYPE type;

    public GamePokemonOutputDto(Long id, Long nationalDex, @NotBlank String name, TypeEnum.POKEMON_TYPE type) {
        this.id = id;
        this.nationalDex = nationalDex;
        this.name = name;
        this.type = type;
    }
}
