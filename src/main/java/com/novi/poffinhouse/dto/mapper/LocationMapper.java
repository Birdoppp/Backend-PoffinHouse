package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.region.Location;

public class LocationMapper {

    public static Location toModel(LocationInputDto inputDto) {
        Location location = new Location();
        location.setName(inputDto.getName());
        location.setDescription(inputDto.getDescription());
        location.setCoordinateX(inputDto.getCoordinateX());
        location.setCoordinateY(inputDto.getCoordinateY());
        location.setMap(inputDto.getMap());
        return location;
    }

    public static LocationOutputDto toOutputDto(Location location) {
        LocationOutputDto outputDto = new LocationOutputDto();
        outputDto.setId(location.getId());
        outputDto.setName(location.getName());
        outputDto.setDescription(location.getDescription());
        outputDto.setCoordinateX(location.getCoordinateX());
        outputDto.setCoordinateY(location.getCoordinateY());
        outputDto.setRegionName(location.getMap().getRegionName());


        return outputDto;
    }
}
