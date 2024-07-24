package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.LocationRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class LocationService {

    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationOutputDto createLocation(LocationInputDto inputDto) {
        Location location = LocationMapper.toEntity(inputDto);
        Location savedLocation = locationRepository.save(location);
        return LocationMapper.toOutputDto(savedLocation);
    }

    public LocationOutputDto getLocationById(int id) {
        Location location = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
        return LocationMapper.toOutputDto(location);
    }

    public List<LocationOutputDto> getAllLocations() {
        List<Location> locations = locationRepository.findAll();
        return locations.stream().map(LocationMapper::toOutputDto).collect(Collectors.toList());
    }

    public LocationOutputDto updateLocation(int id, LocationInputDto inputDto) {
        Location existingLocation = locationRepository.findById(id).orElseThrow(() -> new RuntimeException("Location not found"));
        existingLocation.setName(inputDto.getName());
        existingLocation.setDescription(inputDto.getDescription());
        existingLocation.setCoordinateX(inputDto.getCoordinateX());
        existingLocation.setCoordinateY(inputDto.getCoordinateY());
        Location updatedLocation = locationRepository.save(existingLocation);
        return LocationMapper.toOutputDto(updatedLocation);
    }

    public void deleteLocation(int id) {
        locationRepository.deleteById(id);
    }
}
