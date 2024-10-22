package com.novi.poffinhouse.dto.input;

import com.novi.poffinhouse.util.PreferencesEnum;
import com.novi.poffinhouse.util.ValidEnum;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OwnedPokemonInputDto {
    @Positive
    private Long gameId;
    @NotBlank
    private String pokemonName;
    @Size(max = 12)
    private String nickname;
    @ValidEnum(enumClass = PreferencesEnum.NATURE.class, message = "Invalid nature.")
    private PreferencesEnum.NATURE nature;
    @Size(min = 1, max = 12)
    private String caughtByTrainerName;


    private Integer beauty;
    private Integer coolness;
    private Integer cuteness;
    private Integer cleverness;
    private Integer toughness;

}
