package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.game.GameMap;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.GameMapRepository;
import com.novi.poffinhouse.repositories.LocationRepository;
import com.novi.poffinhouse.util.LocationValidator;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
public class LocationService {

    private final LocationRepository locationRepository;
    private final GameMapRepository gameMapRepository;

    public LocationService(LocationRepository locationRepository, GameMapRepository gameMapRepository) {
        this.locationRepository = locationRepository;
        this.gameMapRepository = gameMapRepository;
    }

    public LocationOutputDto createLocation(@Valid LocationInputDto inputDto) {
        if (inputDto.getGameMapId() == null) {
            throw new IllegalArgumentException("GameMapId is required");
        }
        GameMap gameMap = gameMapRepository.findById(inputDto.getGameMapId())
                .orElseThrow(() -> new IllegalArgumentException("GameMap with id " + inputDto.getGameMapId() + " not found."));

        RegionMap regionMap = gameMap.getRegionMap();

        LocationValidator.validateCoordinates(inputDto, regionMap);
        LocationValidator.checkDuplicateCoordinates(inputDto, gameMap);

        Location location = LocationMapper.toEntity(inputDto);
        location.setGameMap(gameMap);
        Location savedLocation = locationRepository.save(location);
        return LocationMapper.toOutputDto(savedLocation);
    }

    public LocationOutputDto getLocationById(Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            return LocationMapper.toOutputDto(optionalLocation.get());
        }
        throw new IllegalArgumentException("Location with id " + id + " not found.");
    }

    public List<LocationOutputDto> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public LocationOutputDto adjustLocation(Long id, @Valid LocationInputDto inputDto) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + " not found."));

        GameMap gameMap = gameMapRepository.findById(existingLocation.getGameMap().getId())
                .orElseThrow(() -> new IllegalArgumentException("GameMap with id " + existingLocation.getGameMap().getId() + " not found."));

        RegionMap regionMap = gameMap.getRegionMap();
        LocationValidator.validateCoordinates(inputDto, regionMap);
        LocationValidator.checkDuplicateCoordinates(inputDto, gameMap);

        existingLocation.setName(inputDto.getName());
        existingLocation.setDescription(inputDto.getDescription());
        existingLocation.setCoordinateX(inputDto.getCoordinateX());
        existingLocation.setCoordinateY(inputDto.getCoordinateY());

        Location updatedLocation = locationRepository.save(existingLocation);
        return LocationMapper.toOutputDto(updatedLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}