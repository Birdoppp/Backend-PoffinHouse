package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.mapper.BerryPlantingSiteMapper;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.exceptions.ResourceNotFoundException;
import com.novi.poffinhouse.exceptions.SlotOccupiedException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.region.BerryPlantingSite;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.BerryPlantingSiteRepository;
import com.novi.poffinhouse.repositories.LocationRepository;
import com.novi.poffinhouse.repositories.BerryRepository;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

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

    public void deleteBerryPlantingSite(Long id) {
        berryPlantingSiteRepository.deleteById(id);
    }


    // Assigning Berries to Planting Sites

    @Transactional
    public void plantBerryInSoilSlot(Long berryPlantingSiteId, Long slotId, Long berryId) {
        BerryPlantingSite site = berryPlantingSiteRepository.findById(berryPlantingSiteId)
                .orElseThrow(() -> new ResourceNotFoundException("BerryPlantingSite not found with id: " + berryPlantingSiteId));

        Berry berry = berryRepository.findById(berryId)
                .orElseThrow(() -> new ResourceNotFoundException("Berry not found with id: " + berryId));

        if (slotId < 1 || slotId > site.getSoilSlots()) {
            throw new IllegalArgumentException("Slot number " + slotId + " is out of bounds. Must be between 1 and " + site.getSoilSlots());
        }

        // Check if the slot is already occupied
        if (site.getPlantedBerriesBySlots().containsKey(slotId.intValue())) {
            throw new SlotOccupiedException("Slot " + slotId + " is already occupied by another berry.");
        }

        site.getPlantedBerriesBySlots().put(slotId.intValue(), berry);
        berryPlantingSiteRepository.save(site);
    }
//
//    @Transactional
//    public void removeBerry(Long siteId, int slot) {
//        BerryPlantingSite site = berryPlantingSiteRepository.findById(siteId)
//                .orElseThrow(() -> new EntityNotFoundException("Berry Planting Site not found"));
//
//        // Check if the slot has a berry planted
//        if (!site.getPlantedBerriesBySlots().containsKey(slot)) {
//            throw new IllegalArgumentException("No berry planted in this slot.");
//        }
//        site.getPlantedBerriesBySlots().remove(slot);
//        berryPlantingSiteRepository.save(site);
//    }
}