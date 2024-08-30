package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class LocationService {
    private final LocationRepository locationRepository;

    public LocationService(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    public LocationOutputDto createLocation(LocationInputDto locationInputDto) {
        Location location = LocationMapper.toEntity(locationInputDto);
        location = locationRepository.save(location);
        return LocationMapper.toOutputDto(location);
    }

    public List<LocationOutputDto> getAllLocations() {
        return locationRepository.findAll().stream()
                .map(LocationMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public LocationOutputDto getLocationById(Long id) {
        Location location = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));
        return LocationMapper.toOutputDto(location);
    }

    public LocationOutputDto updateLocation(Long id, LocationInputDto updatedLocationDto) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Location not found"));

        existingLocation.setName(updatedLocationDto.getName());
        existingLocation.setDescription(updatedLocationDto.getDescription());
        existingLocation.setCoordinateX(updatedLocationDto.getCoordinateX());
        existingLocation.setCoordinateY(updatedLocationDto.getCoordinateY());

        Location updatedLocation = locationRepository.save(existingLocation);
        return LocationMapper.toOutputDto(updatedLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }

}
