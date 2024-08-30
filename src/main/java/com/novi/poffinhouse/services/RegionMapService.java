package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.mapper.RegionMapMapper;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.models.region.RegionMap;
import com.novi.poffinhouse.repositories.RegionMapRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
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

    public RegionMapOutputDto getRegionMapById(Long id) {
        RegionMap regionMap = regionMapRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Map not found"));
        return RegionMapMapper.toOutputDto(regionMap);
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

    public void deleteRegionMap(Long id) {
        regionMapRepository.deleteById(id);
    }

}
