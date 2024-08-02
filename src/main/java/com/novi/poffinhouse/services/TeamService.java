package com.novi.poffinhouse.services;

//import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.pokemon.Team;
import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.repositories.TeamRepository;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeamService {

    private final TeamRepository teamRepository;

    private final PokemonRepository pokemonRepository;

    public TeamService(TeamRepository teamRepository, PokemonRepository pokemonRepository) {
        this.teamRepository = teamRepository;
        this.pokemonRepository = pokemonRepository;
    }

    public List<Team> getAllTeams() {
        return teamRepository.findAll();
    }

    public Team getTeamById(int id) {
        return teamRepository.findById(id).orElse(null);
    }

//    public Team saveTeam(Team team) {
//        if (team.getPokemons().size() > Team.MAX_POKEMON) {
//            throw new IllegalStateException("A team can only have up to 6 Pokemon.");
//        }
//        return teamRepository.save(team);
//    }

//    public Team addPokemonToTeam(int teamId, Pokemon pokemon) {
//        Team team = getTeamById(teamId);
//        if (team == null) {
//            throw new IllegalArgumentException("Team not found");
//        }
//        team.addPokemon(pokemon);
//        return teamRepository.save(team);
//    }

//    public void removePokemonFromTeam(int teamId, int pokemonId) {
//        Team team = getTeamById(teamId);
//        Pokemon pokemon = pokemonRepository.findById(pokemonId).orElse(null);
//        if (team != null && pokemon != null && team.getPokemons().contains(pokemon)) {
//            team.removePokemon(pokemon);
//            teamRepository.save(team);
//        }
//    }
}
