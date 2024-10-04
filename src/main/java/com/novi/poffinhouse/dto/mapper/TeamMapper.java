package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.dto.output.game.TeamOwnedPokemonOutputDto;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.Team;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import com.novi.poffinhouse.repositories.GameRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TeamMapper {

    private final GameRepository gameRepository;

    public TeamMapper(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public Team toEntity(TeamInputDto inputDto, List<OwnedPokemon> ownedPokemonList) {
        Team team = new Team();
        team.setDescription(inputDto.getDescription());

        // Retrieve the Game entity using the gameId from the inputDto
        Game game = gameRepository.findById(inputDto.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + inputDto.getGameId() + " not found."));
        team.setGame(game);

        // Check if each OwnedPokemon belongs to the specified Game
        for (OwnedPokemon ownedPokemon : ownedPokemonList) {
            if (!ownedPokemon.getGame().getId().equals(game.getId())) {
                throw new IllegalArgumentException("OwnedPokemon with id " + ownedPokemon.getId() + " does not belong to the specified Game.");
            }
        }

        team.setOwnedPokemon(ownedPokemonList);
        return team;
    }

    public TeamOutputDto toDto(Team team) {
        TeamOutputDto outputDto = new TeamOutputDto();
        outputDto.setGameId(team.getGame().getId());
        outputDto.setId(team.getId());
        outputDto.setDescription(team.getDescription());
        outputDto.setOwnedPokemon(team.getOwnedPokemon().stream()
                .map(this::toTeamOwnedPokemonOutputDto)
                .collect(Collectors.toList()));
        return outputDto;
    }

    private TeamOwnedPokemonOutputDto toTeamOwnedPokemonOutputDto(OwnedPokemon ownedPokemon) {
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
