package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.models.pokemon.Team;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    public Team toEntity(TeamInputDto dto, List<OwnedPokemon> ownedPokemonList) {
        Team team = new Team();
        team.setDescription(dto.getDescription());
        team.setOwnedPokemon(ownedPokemonList);
        return team;
    }

    public TeamOutputDto toDto(Team team) {
        TeamOutputDto dto = new TeamOutputDto();
        dto.setId(team.getId());
        dto.setDescription(team.getDescription());
        dto.setOwnedPokemon(
                team.getOwnedPokemon().stream()
                        .map(this::toOwnedPokemonOutputDto)
                        .collect(Collectors.toList())
        );
        return dto;
    }

    private OwnedPokemonOutputDto toOwnedPokemonOutputDto(OwnedPokemon ownedPokemon) {
        OwnedPokemonOutputDto dto = new OwnedPokemonOutputDto();
        dto.setId(ownedPokemon.getId());
        dto.setPokemonName(ownedPokemon.getPokemon().getName());
        dto.setNickname(ownedPokemon.getNickname());
        dto.setNature(ownedPokemon.getNature().name());
        dto.setCaughtByTrainerName(ownedPokemon.getCaughtByTrainerName());
        dto.setBeauty(ownedPokemon.getBeauty());
        dto.setCoolness(ownedPokemon.getCoolness());
        dto.setCuteness(ownedPokemon.getCuteness());
        dto.setCleverness(ownedPokemon.getCleverness());
        dto.setToughness(ownedPokemon.getToughness());

        return dto;
    }
}
