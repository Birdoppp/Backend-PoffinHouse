package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDtoShort;
import com.novi.poffinhouse.models.region.Location;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LocationMapper {

    public static Location toEntity(LocationInputDto inputDto) {
        Location location = new Location();
        location.setName(inputDto.getName());
        location.setDescription(inputDto.getDescription());
        location.setCoordinateX(inputDto.getCoordinateX());
        location.setCoordinateY(inputDto.getCoordinateY());

        return location;
    }


    public static LocationOutputDto toOutputDto(Location location) {
        LocationOutputDto outputDto = new LocationOutputDto();
        outputDto.setId(location.getId());
        outputDto.setName(location.getName());
        outputDto.setDescription(location.getDescription());
        outputDto.setCoordinateX(location.getCoordinateX());
        outputDto.setCoordinateY(location.getCoordinateY());
        outputDto.setGameMapId(location.getGameMap().getId());
        outputDto.setBerryPlantingSites(location.getBerryPlantingSites().stream()
                .map(BerryPlantingSiteMapper::toOutputDto)
                .collect(Collectors.toList()));

        return outputDto;
    }

    public static LocationOutputDtoShort toOutputDtoShort(Location location) {
        LocationOutputDtoShort outputDto = new LocationOutputDtoShort();
        outputDto.setId(location.getId());
        outputDto.setName(location.getName());
        outputDto.setCoordinateX(location.getCoordinateX());
        outputDto.setCoordinateY(location.getCoordinateY());
        outputDto.setTotalBerryPlantingSites(location.getBerryPlantingSites().size());
        outputDto.setTotalPlantedBerries(location.getBerryPlantingSites().stream()
                .mapToInt(berryPlantingSite -> berryPlantingSite.getPlantedBerriesBySlots().size())
                .sum());

        return outputDto;
    }
}