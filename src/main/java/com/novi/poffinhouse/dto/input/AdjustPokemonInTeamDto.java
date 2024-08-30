package com.novi.poffinhouse.dto.input;

import lombok.Data;

import java.util.List;

@Data
public class AdjustPokemonInTeamDto {
    private List<Long> ownedPokemonIds;
}
