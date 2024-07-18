package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.dto.mapper.MapMapper;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.Map;
import com.novi.poffinhouse.repositories.MapRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MapService {

    private final MapRepository mapRepository;

    @Autowired
    public MapService(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public MapOutputDto createMap(MapInputDto inputDto) {
        Map map = MapMapper.toEntity(inputDto);
        Map savedMap = mapRepository.save(map);
        return MapMapper.toDto(savedMap);
    }

    public void addLocationToMap(String regionName, Location location) {
        Map map = mapRepository.findMapByRegionName(regionName)
                .orElseThrow(() -> new IllegalArgumentException("Map not found with name: " + regionName));
        map.addLocation(location);
        mapRepository.save(map);
    }
}
