package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.*;
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

    @PatchMapping("/{id}")
    public ResponseEntity<GameOutputDto> adjustGame(@PathVariable Long id, @Valid @RequestBody GameAdjustInputDto adjustInputDto) {
        GameOutputDto updatedGame = gameService.adjustGame(id, adjustInputDto);
        return ResponseEntity.ok(updatedGame);
    }

    //Pokemon list
    @PatchMapping("/{id}/pokemon-list")
    public ResponseEntity<GameOutputDto> adjustPokemonList(@PathVariable Long id, @Valid @RequestBody AdjustListDto adjustListDto) {
        GameOutputDto updatedGame = gameService.adjustPokemonList(id, adjustListDto);
        return ResponseEntity.ok(updatedGame);
    }

    //OwnedPokemon list is separate

    //Team is separate

    //Berries list
    @PatchMapping("/{id}/berry-list")
    public ResponseEntity<GameOutputDto> adjustBerryList(@PathVariable Long id, @Valid @RequestBody AdjustListDto adjustListDto) {
        GameOutputDto updatedGame = gameService.adjustBerryList(id, adjustListDto);
        return ResponseEntity.ok(updatedGame);
    }


    @DeleteMapping
    public ResponseEntity<String> deleteGame(@RequestBody Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Game with id " + id + " deleted.");
    }


}
