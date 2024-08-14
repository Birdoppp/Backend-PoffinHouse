//package com.novi.poffinhouse.controllers;
//
//import com.novi.poffinhouse.models.pokemon.Pokemon;
//import com.novi.poffinhouse.models.pokemon.Team;
//import com.novi.poffinhouse.services.TeamService;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.List;
//
//@RestController
//@RequestMapping("/teams")
//public class TeamController {
//    private final TeamService teamService;
//
//    public TeamController(TeamService teamService) {
//        this.teamService = teamService;
//    }
//
//    @GetMapping
//    public List<Team> getAllTeams() {
//        return teamService.getAllTeams();
//    }
//
//    @GetMapping("/{id}")
//    public Team getTeamById(@PathVariable int id) {
//        return teamService.getTeamById(id);
//    }
//
//    @PostMapping
//    public Team createTeam(@RequestBody Team team) {
//        return teamService.saveTeam(team);
//    }
//
//    @PostMapping("/{teamId}/pokemon")
//    public Team addPokemonToTeam(@PathVariable int teamId, @RequestBody Pokemon pokemon) {
//        return teamService.addPokemonToTeam(teamId, pokemon);
//    }
//
//    @DeleteMapping("/{teamId}/pokemon/{pokemonId}")
//    public void removePokemonFromTeam(@PathVariable int teamId, @PathVariable int pokemonId) {
//        teamService.removePokemonFromTeam(teamId, pokemonId);
//    }
//}
