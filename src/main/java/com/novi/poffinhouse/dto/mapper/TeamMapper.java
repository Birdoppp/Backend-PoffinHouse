package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.dto.output.game.TeamOwnedPokemonOutputDto;
import com.novi.poffinhouse.models.game.Team;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public static Team toEntity(TeamInputDto inputDto, List<OwnedPokemon> ownedPokemonList) {
        Team team = new Team();
        team.setDescription(inputDto.getDescription());
        team.setOwnedPokemon(ownedPokemonList);

        return team;
    }

    public static TeamOutputDto toDto(Team team) {
        TeamOutputDto outputDto = new TeamOutputDto();
        outputDto.setGameId(team.getGame().getId());
        outputDto.setId(team.getId());
        outputDto.setDescription(team.getDescription());
        outputDto.setOwnedPokemon(team.getOwnedPokemon().stream()
                .map(TeamMapper::toTeamOwnedPokemonOutputDto)
                .collect(Collectors.toList()));
        return outputDto;
    }

    private static TeamOwnedPokemonOutputDto toTeamOwnedPokemonOutputDto(OwnedPokemon ownedPokemon) {
        TeamOwnedPokemonOutputDto dto = new TeamOwnedPokemonOutputDto();
        dto.setId(ownedPokemon.getId());
        dto.setPokemonName(ownedPokemon.getPokemon().getName());
        dto.setNickname(ownedPokemon.getNickname());
        dto.setNature(ownedPokemon.getNature().name());

        dto.setBeauty(ownedPokemon.getBeauty());
        dto.setCoolness(ownedPokemon.getCoolness());
        dto.setCuteness(ownedPokemon.getCuteness());
        dto.setCleverness(ownedPokemon.getCleverness());
        dto.setToughness(ownedPokemon.getToughness());

        return dto;
    }
}
