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
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public TeamOutputDto createTeam(TeamInputDto dto) {
        if (dto.getOwnedPokemonIds().size() > 6) {
            throw new IllegalArgumentException("A team can have a maximum of 6 Pokémon.");
        }
        List<OwnedPokemon> ownedPokemonList = dto.getOwnedPokemonIds().stream()
                .map(id -> ownedPokemonRepository.findById(id)
                        .orElseThrow(() -> new EntityNotFoundException("OwnedPokemon not found with id " + id)))
                .collect(Collectors.toList());

        Team team = teamMapper.toEntity(dto, ownedPokemonList);
        team = teamRepository.save(team);
        return teamMapper.toDto(team);
    }

    public TeamOutputDto adjustPokemonInTeam(Long teamId, AdjustPokemonInTeamDto dto) {
        if (dto.getOwnedPokemonIds().size() > 6) {
            throw new IllegalArgumentException("A team can have a maximum of 6 Pokémon.");
        }
        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new EntityNotFoundException("Team not found with id " + teamId));

        // Clear the existing list of Pokémon
        team.getOwnedPokemon().clear();

        // Add the new list of Pokémon
        List<OwnedPokemon> ownedPokemonList = dto.getOwnedPokemonIds().stream()
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