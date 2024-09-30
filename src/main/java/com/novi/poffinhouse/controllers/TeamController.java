package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.TeamInputDto;
import com.novi.poffinhouse.dto.output.TeamOutputDto;
import com.novi.poffinhouse.dto.input.AdjustPokemonInTeamDto;
import com.novi.poffinhouse.services.TeamService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/teams")
public class TeamController {

    private final TeamService teamService;

    public TeamController(TeamService teamService) {
        this.teamService = teamService;
    }

        //    Post/Creation of Team happens in Game

    @PostMapping
    public ResponseEntity<TeamOutputDto> createTeam(@RequestBody TeamInputDto dto) {
        TeamOutputDto teamOutputDto = teamService.createTeam(dto);
        return ResponseEntity.ok(teamOutputDto);
    }

    @GetMapping("/{id}")
    public ResponseEntity<TeamOutputDto> getTeamById(@PathVariable Long id) {
        TeamOutputDto teamOutputDto = teamService.getTeamById(id);
        return ResponseEntity.ok(teamOutputDto);
    }
    @PutMapping("/{teamId}/pokemon")
    public ResponseEntity<TeamOutputDto> adjustPokemonInTeam(@PathVariable Long teamId, @RequestBody AdjustPokemonInTeamDto dto) {
        TeamOutputDto teamOutputDto = teamService.adjustPokemonInTeam(teamId, dto);
        return ResponseEntity.ok(teamOutputDto);
    }

}
