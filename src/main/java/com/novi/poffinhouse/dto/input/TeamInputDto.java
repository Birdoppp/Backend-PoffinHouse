package com.novi.poffinhouse.dto.input;

import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.util.List;
@Data
public class TeamInputDto {
    @Positive
    private Long gameId;
    private String description;
    private List<Long> ownedPokemonIds;
}

