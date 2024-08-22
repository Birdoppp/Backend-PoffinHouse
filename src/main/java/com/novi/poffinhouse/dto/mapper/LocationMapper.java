package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.MapRepository;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class LocationMapper {

    private static MapRepository mapRepository;

    public LocationMapper(MapRepository mapRepository) {
        this.mapRepository = mapRepository;
    }

    public static Location toEntity(LocationInputDto inputDto) {
        Location location = new Location();
        location.setName(inputDto.getName());
        location.setDescription(inputDto.getDescription());
        location.setCoordinateX(inputDto.getCoordinateX());
        location.setCoordinateY(inputDto.getCoordinateY());
        location.setRegionMap(mapRepository.findById(inputDto.getRegionMapId()).orElse(null));

        return location;
    }

    public static LocationOutputDto toOutputDto(Location location) {
        LocationOutputDto outputDto = new LocationOutputDto();
        outputDto.setId(location.getId());
        outputDto.setName(location.getName());
        outputDto.setDescription(location.getDescription());
        outputDto.setCoordinateX(location.getCoordinateX());
        outputDto.setCoordinateY(location.getCoordinateY());
        outputDto.setRegionMapId(location.getRegionMap().getId());
        outputDto.setBerryPlantingSites(location.getBerryPlantingSites().stream()
                .map(BerryPlantingSiteMapper::toOutputDto)
                .collect(Collectors.toList()));
        return outputDto;
    }
}