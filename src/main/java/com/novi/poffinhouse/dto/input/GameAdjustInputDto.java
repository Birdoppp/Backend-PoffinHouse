package com.novi.poffinhouse.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameAdjustInputDto {
    private String versionName;
    @NotNull
    @Positive
    private Integer generation;
    private String description;

    @Valid
    private GameIdListSetterInputDto pokemonInput;
    @Valid
    private GameIdListSetterInputDto berryInput;
}