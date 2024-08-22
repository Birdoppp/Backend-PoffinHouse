package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.mapper.RegionMapMapper;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.RegionMapRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RegionMapService {
    private final RegionMapRepository regionMapRepository;

    public RegionMapService(RegionMapRepository regionMapRepository) {
        this.regionMapRepository = regionMapRepository;
    }

    public RegionMapOutputDto createRegionMap(RegionMapInputDto regionMapInputDto) {
        RegionMap regionMap = RegionMapMapper.toEntity(regionMapInputDto);
        regionMap = regionMapRepository.save(regionMap);
        return RegionMapMapper.toOutputDto(regionMap);
    }

    public List<RegionMapOutputDto> getAllRegionMaps() {
        return regionMapRepository.findAll().stream()
                .map(RegionMapMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public RegionMap getRegionMapById(Long id) {
        return regionMapRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Map not found"));
    }

    public RegionMap updateRegionMap(Long id, RegionMap updatedMap) {
        RegionMap existingMap = getRegionMapById(id);
        existingMap.setRegionName(updatedMap.getRegionName());
        existingMap.setSizeXAxis(updatedMap.getSizeXAxis());
        existingMap.setSizeYAxis(updatedMap.getSizeYAxis());
        return regionMapRepository.save(existingMap);
    }

    public void deleteRegionMap(Long id) {
        regionMapRepository.deleteById(id);
    }

    public void addLocationToMap(Long mapId, Location location) {
        RegionMap map = getRegionMapById(mapId);
        map.addLocation(location);
        regionMapRepository.save(map);
    }
}
