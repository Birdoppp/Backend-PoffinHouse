package com.novi.poffinhouse.util;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.game.GameMap;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.RegionMap;

import java.util.Optional;

public class LocationValidator {

    public static void validateCoordinates(LocationInputDto inputDto, RegionMap regionMap) {
        if (inputDto.getCoordinateX() < 0 || inputDto.getCoordinateX() >= regionMap.getSizeXAxis() ||
                inputDto.getCoordinateY() < 0 || inputDto.getCoordinateY() >= regionMap.getSizeYAxis()) {
            throw new IllegalArgumentException("Location coordinates are out of bounds of the RegionMap.");
        }
    }

    public static void checkDuplicateCoordinates(LocationInputDto inputDto, GameMap gameMap) {
        Optional<Location> existingLocation = gameMap.getLocations().stream()
                .filter(location -> location.getCoordinateX() == inputDto.getCoordinateX() && location.getCoordinateY() == inputDto.getCoordinateY())
                .findFirst();

        if (existingLocation.isPresent()) {
            Location location = existingLocation.get();
            LocationOutputDto existingLocationDto = LocationMapper.toOutputDto(location);
            throw new IllegalArgumentException("A location with the same coordinates already exists: " + existingLocationDto);
        }
    }
}