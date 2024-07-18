package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.models.region.Map;

public class MapMapper {

    public static Map toEntity(MapInputDto dto) {
        Map map = new Map();
        map.setRegionName(dto.getRegionName());
        map.setSizeXAxis(dto.getSizeXAxis());
        map.setSizeYAxis(dto.getSizeYAxis());
        return map;
    }

    public static MapOutputDto toDto(Map map) {
        MapOutputDto dto = new MapOutputDto();
        dto.setId(map.getId());
        dto.setRegionName(map.getRegionName());
        dto.setSizeXAxis(map.getSizeXAxis());
        dto.setSizeYAxis(map.getSizeYAxis());
        dto.setLocations(map.getLocations());
        return dto;
    }
}
