package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.BerryPlantingSiteInputDto;
import com.novi.poffinhouse.dto.output.BerryPlantingSiteOutputDto;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.region.BerryPlantingSite;
import com.novi.poffinhouse.models.region.Location;
import com.novi.poffinhouse.repositories.BerryRepository;

import java.util.HashMap;
import java.util.Map;

public class BerryPlantingSiteMapper {


    private final BerryRepository berryRepository;

    public BerryPlantingSiteMapper(BerryRepository berryRepository) {
        this.berryRepository = berryRepository;
    }

    public BerryPlantingSite toEntity(BerryPlantingSiteInputDto inputDto, Location location) {
        BerryPlantingSite berryPlantingSite = new BerryPlantingSite(inputDto.getSoilSlots());
        berryPlantingSite.setDescription(inputDto.getDescription());
        berryPlantingSite.setLocation(location);

        // Map each berry ID to its corresponding slot, finding the Berry entity by ID
        Map<Integer, Berry> plantedBerriesBySlots = new HashMap<>();
        for (Map.Entry<Integer, Long> entry : inputDto.getPlantedBerriesBySlots().entrySet()) {
            Integer slot = entry.getKey();
            Long berryId = entry.getValue();

            if (berryId != null) {
                Berry berry = berryRepository.findById(berryId)
                        .orElseThrow(() -> new IllegalArgumentException("Berry not found with ID: " + berryId));
                plantedBerriesBySlots.put(slot, berry);
            }
        }

        berryPlantingSite.setPlantedBerriesBySlots(plantedBerriesBySlots);
        return berryPlantingSite;
    }

    public static BerryPlantingSiteOutputDto toOutputDto(BerryPlantingSite berryPlantingSite) {
        BerryPlantingSiteOutputDto outputDto = new BerryPlantingSiteOutputDto();
        outputDto.setId(berryPlantingSite.getId());
        outputDto.setLocationId(berryPlantingSite.getLocation().getId());
        outputDto.setDescription(berryPlantingSite.getDescription());
        outputDto.setSoilSlots(berryPlantingSite.getSoilSlots());

        Map<Integer, Long> berriesBySlotId = new HashMap<>();
        for (int i = 1; i <= berryPlantingSite.getSoilSlots(); i++) {
            if (berryPlantingSite.getPlantedBerriesBySlots().containsKey(i)) {
                berriesBySlotId.put(i, berryPlantingSite.getPlantedBerriesBySlots().get(i).getId());
            } else {
                berriesBySlotId.put(i, null);  // No berry in this slot
            }
        }

        outputDto.setPlantedBerriesBySlots(berriesBySlotId);
        return outputDto;
    }
}