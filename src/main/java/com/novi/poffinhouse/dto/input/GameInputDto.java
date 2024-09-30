package com.novi.poffinhouse.dto.input;

import jakarta.validation.Valid;
import lombok.Data;

@Data
public class GameInputDto {
    private String versionName;
    private int generation;
    private String description;

    private Long regionMapId;
    @Valid
    private GameIdListSetterInputDto pokemonInput;
    @Valid
    private GameIdListSetterInputDto berryInput;
}