package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.MapInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.dto.output.MapOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.models.region.Map;

import java.util.ArrayList;
import java.util.List;

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
        if (map.getLocations() != null){
            List<LocationOutputDto> locationOutputDtoList = new ArrayList<>();
            for(Location location : map.getLocations()){
                locationOutputDtoList.add(LocationMapper.toOutputDto(location));
            }
            dto.setLocations(locationOutputDtoList);
        }
        return dto;
    }
}
