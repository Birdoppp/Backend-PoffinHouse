package com.novi.poffinhouse.dto.output;

import com.novi.poffinhouse.dto.output.game.TeamOwnedPokemonOutputDto;
import lombok.Data;

import java.util.List;

@Data
public class TeamOutputDto {
    private Long gameId;
    private Long id;
    private String description;
    private List<TeamOwnedPokemonOutputDto> ownedPokemon;
}