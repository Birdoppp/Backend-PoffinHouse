package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.LocationInputDto;
import com.novi.poffinhouse.dto.mapper.LocationMapper;
import com.novi.poffinhouse.dto.output.LocationOutputDto;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.LocationRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Transactional
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

    public LocationOutputDto getLocationById(Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);
        if (optionalLocation.isPresent()) {
            return LocationMapper.toOutputDto(optionalLocation.get());
        }
        throw new IllegalArgumentException("Location with id " + id + " not found.");
    }

    public List<LocationOutputDto> getAllLocations() {
        return locationRepository.findAll()
                .stream()
                .map(LocationMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public LocationOutputDto updateLocation(Long id, LocationInputDto inputDto) {
        Location existingLocation = locationRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Location with id " + id + " not found."));

        LocationMapper.updateEntity(existingLocation, inputDto);

        Location updatedLocation = locationRepository.save(existingLocation);
        return LocationMapper.toOutputDto(updatedLocation);
    }

    public void deleteLocation(Long id) {
        locationRepository.deleteById(id);
    }
}