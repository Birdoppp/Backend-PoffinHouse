package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.*;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.dto.output.game.GameOutputDto;
import com.novi.poffinhouse.services.GameService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/games")
public class GameController {

    private final GameService gameService;

    public GameController(GameService gameService) {
        this.gameService = gameService;
    }

    @PostMapping
    public ResponseEntity<GameOutputDto> createGame(@Valid @RequestBody GameInputDto gameInputDto) {
        GameOutputDto createdGame = gameService.createGame(gameInputDto);
        return ResponseEntity.ok(createdGame);
    }

    @GetMapping("/{id}")
    public ResponseEntity<GameOutputDto> getGameById(@PathVariable Long id) {
//        Authentication authentication = SecurityContextHolder.getContext().getAuthentication().getName();

        GameOutputDto game = gameService.getGameById(id);
        return ResponseEntity.ok(game);
    }

    @GetMapping("/user/{username}")
    public ResponseEntity<List<GameOutputDto>> getAllGamesByUsername(@PathVariable String username) {
        List<GameOutputDto> games = gameService.getAllGamesByUsername(username);
        return ResponseEntity.ok(games);
    }

    @GetMapping
    public ResponseEntity<List<GameOutputDto>> getAllGames() {
        List<GameOutputDto> games = gameService.getAllGames();
        return ResponseEntity.ok(games);
    }

    @PutMapping("/{id}")
    public ResponseEntity<GameOutputDto> updateGame(@PathVariable Long id, @Valid @RequestBody GameInputDto gameInputDto) {
        GameOutputDto updatedGame = gameService.updateGame(id, gameInputDto);
        return ResponseEntity.ok(updatedGame);
    }

    //Pokemon
    @PatchMapping("/{id}/pokemonList")
    public ResponseEntity<GameOutputDto> patchPokemonList(@PathVariable Long id, @Valid @RequestBody AdjustIdListDto adjustIdListDto) {
        GameOutputDto updatedGame = gameService.patchPokemonList(id, adjustIdListDto);
        return ResponseEntity.ok(updatedGame);
    }

    //OwnedPokemon - other methods in OwnedPokemonController
    @PostMapping("/{id}/ownedPokemon")
    public ResponseEntity<OwnedPokemonOutputDto> createOwnedPokemon(@PathVariable Long id, @Valid @RequestBody OwnedPokemonInputDto ownedPokemonInputDto) {
        OwnedPokemonOutputDto ownedPokemon = gameService.createOwnedPokemon(id, ownedPokemonInputDto);
        return ResponseEntity.ok(ownedPokemon);
    }

    //Team
    @PutMapping("/{id}/team")
    public ResponseEntity<GameOutputDto> updateTeam(@PathVariable Long id, @RequestBody Long teamId) {
        GameOutputDto updatedGame = gameService.updateTeam(id, teamId);
        return ResponseEntity.ok(updatedGame);
    }

    //Berries
    @PatchMapping("/{id}/berryList")
    public ResponseEntity<GameOutputDto> patchBerryList(@PathVariable Long id, @Valid @RequestBody AdjustIdListDto adjustIdListDto) {
        GameOutputDto updatedGame = gameService.patchBerryList(id, adjustIdListDto);
        return ResponseEntity.ok(updatedGame);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteGame(@RequestBody Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Game with id " + id + " deleted.");
    }


}
