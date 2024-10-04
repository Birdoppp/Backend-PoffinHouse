package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;
@Data
public class TeamInputDto {
    private Long gameId;
    private String description;
    private List<Long> ownedPokemonIds;
}

