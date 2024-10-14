package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.mapper.BerryPlantingSiteMapper;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.exceptions.ResourceNotFoundException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.region.BerryPlantingSite;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.BerryPlantingSiteRepository;
import com.novi.poffinhouse.repositories.LocationRepository;
import com.novi.poffinhouse.repositories.BerryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class BerryPlantingSiteService {
    private final BerryPlantingSiteRepository berryPlantingSiteRepository;
    private final LocationRepository locationRepository;
    private final BerryRepository berryRepository;

    public BerryPlantingSiteService(BerryPlantingSiteRepository berryPlantingSiteRepository, LocationRepository locationRepository, BerryRepository berryRepository) {
        this.berryPlantingSiteRepository = berryPlantingSiteRepository;
        this.locationRepository = locationRepository;
        this.berryRepository = berryRepository;
    }

    public BerryPlantingSiteOutputDto createBerryPlantingSite(BerryPlantingSiteInputDto inputDto) {
        Location location = locationRepository.findById(inputDto.getLocationId())
                .orElseThrow(() -> new EntityNotFoundException("Location not found"));
        BerryPlantingSiteMapper berryPlantingSiteMapper = new BerryPlantingSiteMapper(berryRepository);
        BerryPlantingSite berryPlantingSite = berryPlantingSiteMapper.toEntity(inputDto, location);
        berryPlantingSite.setLocation(location);
        berryPlantingSite = berryPlantingSiteRepository.save(berryPlantingSite);
        return BerryPlantingSiteMapper.toOutputDto(berryPlantingSite);
    }

    public List<BerryPlantingSiteOutputDto> getAllBerryPlantingSites() {
        return berryPlantingSiteRepository.findAll().stream()
                .map(BerryPlantingSiteMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public BerryPlantingSiteOutputDto getBerryPlantingSiteById(Long id) {
        BerryPlantingSite berryPlantingSite = berryPlantingSiteRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Berry Planting Site not found"));
        return BerryPlantingSiteMapper.toOutputDto(berryPlantingSite);
    }


    public void adjustBerriesInBerryPlantingSite(Long berryPlantingSiteId, Map<Integer, Long> berriesBySlots) {
        BerryPlantingSite site = berryPlantingSiteRepository.findById(berryPlantingSiteId)
                .orElseThrow(() -> new ResourceNotFoundException("BerryPlantingSite not found with id: " + berryPlantingSiteId));

        for (Map.Entry<Integer, Long> entry : berriesBySlots.entrySet()) {
            Integer slotId = entry.getKey();
            Long berryId = entry.getValue();

            // Validate slot number
            if (slotId < 1 || slotId > site.getSoilSlots()) {
                throw new IllegalArgumentException("Slot number " + slotId + " is out of bounds. Must be between 1 and " + site.getSoilSlots());
            }

            if (berryId == null) {
                // Remove the berry from the slot if berryId is null
                site.getPlantedBerriesBySlots().remove(slotId);
            } else {
                Berry berry = berryRepository.findById(berryId)
                        .orElseThrow(() -> new ResourceNotFoundException("Berry not found with id: " + berryId));
                // Rejects invalid berries
                if (!berry.getValidated()) {
                    throw new IllegalArgumentException("Berry with ID " + berryId + " is not validated and cannot be planted.");
                }

                // Assign the berry to the slot (this will replace any existing berry)
                site.getPlantedBerriesBySlots().put(slotId, berry);
            }
        }

        berryPlantingSiteRepository.save(site);
    }


    public void deleteBerryPlantingSite(Long id) {
        berryPlantingSiteRepository.deleteById(id);
    }
}
