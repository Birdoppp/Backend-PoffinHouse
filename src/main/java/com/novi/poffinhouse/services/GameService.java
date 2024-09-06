package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.output.GameOutputDto;
import com.novi.poffinhouse.dto.mapper.GameMapper;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.repositories.GameRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
    }

    public GameOutputDto createGame(GameInputDto inputDto) {
        Game game = gameMapper.toEntity(inputDto);
        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    public GameOutputDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        return gameMapper.toDto(game);
    }

    public List<GameOutputDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    public GameOutputDto updateGame(Long id, GameInputDto inputDto) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));

        existingGame.setVersionName(inputDto.getVersionName());
        existingGame.setGeneration(inputDto.getGeneration());
        existingGame.setDescription(inputDto.getDescription());

        // Handle related entities updates
        existingGame.setRegionMap(gameMapper.toEntity(inputDto).getRegionMap());
        existingGame.setPokemonList(gameMapper.toEntity(inputDto).getPokemonList());
        existingGame.setOwnedPokemonList(gameMapper.toEntity(inputDto).getOwnedPokemonList());
        existingGame.setBerryList(gameMapper.toEntity(inputDto).getBerryList());

        Game updatedGame = gameRepository.save(existingGame);
        return gameMapper.toDto(updatedGame);
    }

    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Game with id " + id + " not found.");
        }
        gameRepository.deleteById(id);
    }
}