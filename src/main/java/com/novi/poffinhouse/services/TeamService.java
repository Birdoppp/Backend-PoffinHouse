package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AdjustPokemonInTeamDto;
import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.dto.mapper.TeamMapper;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Team;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.TeamRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class TeamService {

    private final TeamRepository teamRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final TeamMapper teamMapper;

    public TeamService(TeamRepository teamRepository, OwnedPokemonRepository ownedPokemonRepository, TeamMapper teamMapper) {
        this.teamRepository = teamRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.teamMapper = teamMapper;
    }

    public TeamOutputDto createTeam(TeamInputDto teamInputDto) {
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

        if (ownedPokemonList.stream().anyMatch(ownedPokemon -> !ownedPokemon.getPokemon().getValidated())) {
            throw new IllegalArgumentException("All Pokémon in the team must be validated.");
        }

        Team team = teamMapper.toEntity(teamInputDto, ownedPokemonList);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    public TeamOutputDto adjustPokemonInTeam(Long teamId, AdjustPokemonInTeamDto adjustPokemonInTeamDto) {
        if (adjustPokemonInTeamDto.getOwnedPokemonIds().size() > 6) {
            throw new IllegalArgumentException("A team can have a maximum of 6 Pokémon.");
        }
        Set<Long> uniquePokemonIds = new HashSet<>(adjustPokemonInTeamDto.getOwnedPokemonIds());
        if (uniquePokemonIds.size() < adjustPokemonInTeamDto.getOwnedPokemonIds().size()) {
            throw new IllegalArgumentException("The team cannot contain the same owned Pokémon twice.");
        }
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id " + teamId));

        team.getOwnedPokemon().clear();

        // Add the new list of Pokémon
        List<OwnedPokemon> ownedPokemonList = adjustPokemonInTeamDto.getOwnedPokemonIds().stream()
                .map(id -> ownedPokemonRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("OwnedPokemon not found with id " + id)))
                .collect(Collectors.toList());

        team.setOwnedPokemon(ownedPokemonList);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    public TeamOutputDto getTeamById(Long id) {
        Team team = teamRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id " + id));
        return teamMapper.toDto(team);
    }
}