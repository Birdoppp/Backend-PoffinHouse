package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.mapper.GameMapMapper;
import com.novi.poffinhouse.dto.output.game.GameMapOutputDto;
import com.novi.poffinhouse.models.game.GameMap;
import com.novi.poffinhouse.repositories.GameMapRepository;
//import com.novi.poffinhouse.repositories.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;

@Validated
@Transactional
@Service
public class GameMapService {
    private final GameMapRepository gameMapRepository;
//    private final LocationRepository locationRepository;

    public GameMapService(GameMapRepository gameMapRepository
//                          LocationRepository locationRepository
    ) {
        this.gameMapRepository = gameMapRepository;
//        this.locationRepository = locationRepository;
    }

    //Create happens during Game creation

    public GameMapOutputDto getGameMapById(Long id) {
        GameMap gameMap = gameMapRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("GameMap with id " + id + " not found."));
        return GameMapMapper.toOutputDto(gameMap);

    }

    public List<GameMapOutputDto> getGameMapsByUsername(String username) {
        return gameMapRepository.findByGame_UserUsername(username).stream()
                .map(GameMapMapper::toOutputDto)
                .toList();
    }

    // Adding and Deleting of Locations are Separate

//    public GameMapOutputDto updateLocationList(Long gameMapId, AdjustListDto adjustListDto) {
//        GameMap gameMap = gameMapRepository.findById(gameMapId)
//                .orElseThrow(() -> new IllegalArgumentException("GameMap with id " + gameMapId + " not found."));
//
//        List<Location> addList = null;
//        List<Location> removeList = null;
//
//        if (adjustListDto.getAddList() != null) {
//            addList = adjustListDto.getAddList().stream()
//                    .map(locationRepository::findById)
//                    .map(location -> location.orElseThrow(() -> new IllegalArgumentException("Location with id " + location + " not found.")))
//                    .filter(location -> !gameMap.getLocations().contains(location))
//                    .toList();
//        }
//        if (adjustListDto.getRemoveList() != null) {
//            removeList = adjustListDto.getRemoveList().stream()
//                    .map(locationRepository::findById)
//                    .map(location -> location.orElseThrow(() -> new IllegalArgumentException("Location with id " + location + " not found.")))
//                    .filter(gameMap.getLocations()::contains)
//                    .toList();
//        }
//        if (addList != null) {
//            gameMap.getLocations().addAll(addList);
//        }
//        if (removeList != null) {
//            gameMap.getLocations().removeAll(removeList);
//        }
//
//        GameMap updatedGameMap = gameMapRepository.save(gameMap);
//
//        return GameMapMapper.toOutputDto(updatedGameMap);
//    }
}
