package com.novi.poffinhouse.dto.output;

import lombok.Data;

import java.util.List;

@Data
public class TeamOutputDto {
    private Long id;
    private String description;
    private List<OwnedPokemonOutputDto> ownedPokemon;
}