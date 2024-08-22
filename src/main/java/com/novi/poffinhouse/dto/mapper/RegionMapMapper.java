package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.RegionMapInputDto;
import com.novi.poffinhouse.dto.output.RegionMapOutputDto;
import com.novi.poffinhouse.models.region.RegionMap;

import java.util.stream.Collectors;

public class RegionMapMapper {

    public static RegionMap toEntity(RegionMapInputDto dto) {
        RegionMap regionMap = new RegionMap();
        regionMap.setRegionName(dto.getRegionName());
        regionMap.setSizeXAxis(dto.getSizeXAxis());
        regionMap.setSizeYAxis(dto.getSizeYAxis());
        return regionMap;
    }

    public static RegionMapOutputDto toOutputDto(RegionMap regionMap) {
        RegionMapOutputDto outputDto = new RegionMapOutputDto();
        outputDto.setId(regionMap.getId());
        outputDto.setRegionName(regionMap.getRegionName());
        outputDto.setSizeXAxis(regionMap.getSizeXAxis());
        outputDto.setSizeYAxis(regionMap.getSizeYAxis());
        if (regionMap.getLocations() != null){
            outputDto.setLocations(regionMap.getLocations().stream()
                    .map(LocationMapper::toOutputDto)
                    .collect(Collectors.toList()));
        }
        return outputDto;
    }
}
