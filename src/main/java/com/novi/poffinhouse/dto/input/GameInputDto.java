package com.novi.poffinhouse.dto.input;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class GameInputDto {
    private String versionName;
    @NotNull
    @Positive
    private Integer generation;
    private String description;
    @NotNull
    @Positive
    private Long regionMapId;
    @Valid
    private GameIdListSetterInputDto pokemonInput;
    @Valid
    private GameIdListSetterInputDto berryInput;
}