package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.dto.mapper.TeamMapper;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import com.novi.poffinhouse.models.game.Team;
import com.novi.poffinhouse.repositories.GameRepository;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.TeamRepository;
import com.novi.poffinhouse.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class  TeamService {

    private final TeamRepository teamRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final GameRepository gameRepository;

    public TeamService(TeamRepository teamRepository, OwnedPokemonRepository ownedPokemonRepository, GameRepository gameRepository) {
        this.teamRepository = teamRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.gameRepository = gameRepository;
    }


    public TeamOutputDto createTeam(@Valid TeamInputDto teamInputDto) {
        Game game = gameRepository.findById(teamInputDto.getGameId())
                .orElseThrow(() -> new EntityNotFoundException("Game not found with id " + teamInputDto.getGameId()));
        if(!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        teamRepository.findByGameId(teamInputDto.getGameId()).ifPresent(existingTeam -> {
            throw new IllegalArgumentException("A team already exists for the given game.");
        });

        if (teamInputDto.getOwnedPokemonIds().size() > 6) {
            throw new IllegalArgumentException("A team can have a maximum of 6 Pokémon.");
        }
        Set<Long> uniquePokemonIds = new HashSet<>(teamInputDto.getOwnedPokemonIds());
        if (uniquePokemonIds.size() < teamInputDto.getOwnedPokemonIds().size()) {
            throw new IllegalArgumentException("The team cannot contain the same owned Pokémon twice.");
        }

        List<OwnedPokemon> ownedPokemonList = teamInputDto.getOwnedPokemonIds().stream()
                .map(id -> ownedPokemonRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("OwnedPokemon not found with id " + id)))
                .collect(Collectors.toList());

        // Check if each OwnedPokemon belongs to the specified Game
        for (OwnedPokemon ownedPokemon : ownedPokemonList) {
            if (!ownedPokemon.getGame().getId().equals(game.getId())) {
                throw new IllegalArgumentException("OwnedPokemon with id " + ownedPokemon.getId() + " does not belong to the specified Game.");
            }
        }

        Team team = TeamMapper.toEntity(teamInputDto, ownedPokemonList);
        team.setGame(game);
        team = teamRepository.save(team);
        return TeamMapper.toDto(team);
    }

    public TeamOutputDto getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id " + id));
        if(!AuthUtil.isAdminOrOwner(team.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        return TeamMapper.toDto(team);
    }

    public TeamOutputDto putPokemonInTeam(Long teamId, List<Long> ownedPokemonIdList) {
        if (ownedPokemonIdList.size() > 6) {
            throw new IllegalArgumentException("A team can have a maximum of 6 Pokémon.");
        }
        Set<Long> uniquePokemonIds = new HashSet<>(ownedPokemonIdList);
        if (uniquePokemonIds.size() < ownedPokemonIdList.size()) {
            throw new IllegalArgumentException("The team cannot contain the same owned Pokémon twice.");
        }
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id " + teamId));
        if(!AuthUtil.isAdminOrOwner(team.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        team.getOwnedPokemon().clear();

        List<OwnedPokemon> ownedPokemonList = ownedPokemonIdList.stream()
                .map(id -> ownedPokemonRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("OwnedPokemon not found with id " + id)))
                .collect(Collectors.toList());

        // Check if each OwnedPokemon belongs to the specified Game
        for (OwnedPokemon ownedPokemon : ownedPokemonList) {
            if (!ownedPokemon.getGame().getId().equals(team.getGame().getId())) {
                throw new IllegalArgumentException("OwnedPokemon with id " + ownedPokemon.getId() + " does not belong to the specified Game.");
            }
        }

        team.setOwnedPokemon(ownedPokemonList);
        team = teamRepository.save(team);
        return TeamMapper.toDto(team);
    }

}