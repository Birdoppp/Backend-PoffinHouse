package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.mapper.RegionMapMapper;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.exceptions.ResourceNotFoundException;
import com.novi.poffinhouse.models.region.RegionMapAtlas;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.RegionMapAtlasRepository;
import com.novi.poffinhouse.repositories.RegionMapRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class RegionMapService {
    private final RegionMapRepository regionMapRepository;
    private final RegionMapAtlasRepository regionMapAtlasRepository;

    public RegionMapService(RegionMapRepository regionMapRepository, RegionMapAtlasRepository regionMapAtlasRepository) {
        this.regionMapRepository = regionMapRepository;
        this.regionMapAtlasRepository = regionMapAtlasRepository;
    }

    public RegionMapOutputDto createRegionMap(RegionMapInputDto regionMapInputDto) {
        RegionMap regionMap = RegionMapMapper.toEntity(regionMapInputDto);
        regionMap = regionMapRepository.save(regionMap);
        return RegionMapMapper.toOutputDto(regionMap);
    }

    public RegionMapOutputDto getRegionMapById(Long id) {
        RegionMap regionMap = regionMapRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Map not found"));
        return RegionMapMapper.toOutputDto(regionMap);
    }

    public List<RegionMapOutputDto> getAllRegionMaps() {
        return regionMapRepository.findAll().stream()
                .map(RegionMapMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public RegionMapOutputDto updateRegionMap(Long id, RegionMapInputDto regionMapInputDto) {
        RegionMap regionMap = regionMapRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Map not found"));
        regionMap.setRegionName(regionMapInputDto.getRegionName());
        regionMap.setSizeXAxis(regionMapInputDto.getSizeXAxis());
        regionMap.setSizeYAxis(regionMapInputDto.getSizeYAxis());
        regionMap = regionMapRepository.save(regionMap);
        return RegionMapMapper.toOutputDto(regionMap);
    }

    public RegionMapOutputDto addAtlas(String fileName, Long regionMapId) {
        Optional<RegionMap> optionalRegionMap = regionMapRepository.findById(regionMapId);
        Optional<RegionMapAtlas> optionalAtlas = regionMapAtlasRepository.findById(fileName);

        if (optionalRegionMap.isPresent() && optionalAtlas.isPresent()) {
            RegionMapAtlas regionMapAtlas = optionalAtlas.get();
            RegionMap regionMap = optionalRegionMap.get();
            regionMap.setRegionMapAtlas(regionMapAtlas);
            RegionMap savedRegionMap = regionMapRepository.save(regionMap);
            return RegionMapMapper.toOutputDto(savedRegionMap);
        } else {
            throw new ResourceNotFoundException("RegionMap or Atlas not found");
        }
    }

}
