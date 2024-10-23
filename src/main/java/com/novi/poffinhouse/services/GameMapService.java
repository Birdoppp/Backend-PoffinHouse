package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.mapper.GameMapMapper;
import com.novi.poffinhouse.dto.output.game.GameMapOutputDto;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
import com.novi.poffinhouse.models.game.gamemap.GameMap;
import com.novi.poffinhouse.repositories.GameMapRepository;
import com.novi.poffinhouse.util.AuthUtil;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Transactional
@Service
public class GameMapService {
    private final GameMapRepository gameMapRepository;

    public GameMapService(GameMapRepository gameMapRepository
    ) {
        this.gameMapRepository = gameMapRepository;
    }

    //Create happens during Game creation

    public GameMapOutputDto getGameMapById(Long id) {
        GameMap gameMap = gameMapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GameMap with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(gameMap.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        return GameMapMapper.toOutputDto(gameMap);

    }

    public List<GameMapOutputDto> getGameMapsByUsername(String username) {
        if (!AuthUtil.isAdminOrOwner(username)) {
            throw new AccessDeniedException();
        }
        return gameMapRepository.findByGame_UserUsername(username).stream()
                .map(GameMapMapper::toOutputDto)
                .toList();
    }

    public List<GameMapOutputDto> getAllGameMaps() {
        return gameMapRepository.findAll().stream()
                .map(GameMapMapper::toOutputDto)
                .toList();
    }

    // Adding and Deleting of Locations are Separate
    // Deletion of GameMap is not allowed
}
