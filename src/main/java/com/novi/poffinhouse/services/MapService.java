package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.dto.mapper.MapMapper;
import com.novi.poffinhouse.exceptions.RecordNotFoundException;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.Map;
import com.novi.poffinhouse.repositories.LocationRepository;
import com.novi.poffinhouse.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MapService {
    private final MapRepository mapRepository;
    private final LocationRepository locationRepository;

    @Autowired
    public MapService(MapRepository mapRepository, LocationRepository locationRepository) {
        this.mapRepository = mapRepository;
        this.locationRepository = locationRepository;
    }

    public MapOutputDto createMap(MapInputDto inputDto) {
        Map map = MapMapper.toEntity(inputDto);
        Map savedMap = mapRepository.save(map);
        return MapMapper.toDto(savedMap);
    }

    public boolean isLocationWithinBounds(Map map, LocationInputDto location) {
        return location.getCoordinateX() >= 0 && location.getCoordinateX() < map.getSizeXAxis() &&
                location.getCoordinateY() >= 0 && location.getCoordinateY() < map.getSizeYAxis();
    }

    public LocationOutputDto addLocationToMap(String regionName, LocationInputDto location) {
        Map map = mapRepository.findMapByRegionName(regionName)
                .orElseThrow(() -> new RecordNotFoundException("No Map was found with name: " + regionName + ", " + location + "has not been added"));
        if (!isLocationWithinBounds(map, location)) {
            throw new IllegalArgumentException("Location is out of map bounds");
        } else {
            location.setMap(map);
            Location l =locationRepository.save(LocationMapper.toModel(location));

            map.addLocation(l);

            mapRepository.save(map);
            return LocationMapper.toOutputDto(l);
        }

    }


    public MapOutputDto getMap(String regionName) {
        Map map = mapRepository.findMapByRegionName(regionName)
                .orElseThrow(() -> new IllegalArgumentException("Map not found with name: " + regionName));
        return MapMapper.toDto(map);
    }

    public List<Location> getAllLocations(String regionName) {
        Map map = mapRepository.findMapByRegionName(regionName)
                .orElseThrow(() -> new IllegalArgumentException("Map not found with name: " + regionName));
        return map.getLocations();
    }
}
