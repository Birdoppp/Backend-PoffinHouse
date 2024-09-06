package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.output.GameOutputDto;
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

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteGame(@PathVariable Long id) {
        gameService.deleteGame(id);
        return ResponseEntity.ok("Game with id " + id + " deleted.");
    }
}