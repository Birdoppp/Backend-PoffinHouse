package com.novi.poffinhouse.controllers;

import com.novi.poffinhouse.dto.output.game.GameMapOutputDto;
import com.novi.poffinhouse.services.GameMapService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/game-maps")
public class GameMapController {
    private final GameMapService gameMapService;

    public GameMapController(GameMapService gameMapService) {
        this.gameMapService = gameMapService;
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<GameMapOutputDto> getGameMapById(@PathVariable Long id) {
        return ResponseEntity.ok(gameMapService.getGameMapById(id));
    }

    @GetMapping("/username/{username}")
    public ResponseEntity<List<GameMapOutputDto>> getGameMapsByUsername(@PathVariable String username) {
        return ResponseEntity.ok(gameMapService.getGameMapsByUsername(username));
    }

    @GetMapping
    public ResponseEntity<List<GameMapOutputDto>> getAllGameMaps() {
        return ResponseEntity.ok(gameMapService.getAllGameMaps());
    }
}
