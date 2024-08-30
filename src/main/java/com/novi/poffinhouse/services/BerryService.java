package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.dto.mapper.BerryMapper;
import com.novi.poffinhouse.exceptions.BerryNotFoundException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.region.BerryPlantingSite;
import com.novi.poffinhouse.repositories.BerryPlantingSiteRepository;
import com.novi.poffinhouse.repositories.BerryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class BerryService {

    private final BerryRepository berryRepository;
    private final BerryPlantingSiteRepository berryPlantingSiteRepository;

    public BerryService(BerryRepository berryRepository, BerryPlantingSiteRepository berryPlantingSiteRepository) {
        this.berryRepository = berryRepository;
        this.berryPlantingSiteRepository = berryPlantingSiteRepository;
    }

    public BerryOutputDto createBerry(BerryInputDto berryInputDto) {
        Berry berry = BerryMapper.toEntity(berryInputDto);
        Berry savedBerry = berryRepository.save(berry);
        return BerryMapper.toOutputDto(savedBerry);
    }

    public List<BerryOutputDto> getAllBerries() {
        return berryRepository.findAll()
                .stream()
                .map(BerryMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public BerryOutputDto getBerryById(Long id) {
        Berry berry = berryRepository.findById(id)
                .orElseThrow(() -> new BerryNotFoundException(id));
        return BerryMapper.toOutputDto(berry);
    }

    public BerryOutputDto updateBerry(Long id, BerryInputDto berryInputDto) {
        Berry berryToUpdate = berryRepository.findById(id)
                .orElseThrow(() -> new BerryNotFoundException(id));

        berryToUpdate.setName(berryInputDto.getName());
        berryToUpdate.setIndexNumber(berryInputDto.getIndexNumber());
        berryToUpdate.setDescription(berryInputDto.getDescription());
        berryToUpdate.setGrowthTime(berryInputDto.getGrowthTime());
        berryToUpdate.setCategoryType(berryInputDto.getCategoryType());
        berryToUpdate.setSpicyPotency(berryInputDto.getSpicyPotency());
        berryToUpdate.setDryPotency(berryInputDto.getDryPotency());
        berryToUpdate.setSweetPotency(berryInputDto.getSweetPotency());
        berryToUpdate.setBitterPotency(berryInputDto.getBitterPotency());
        berryToUpdate.setSourPotency(berryInputDto.getSourPotency());

        Berry updatedBerry = berryRepository.save(berryToUpdate);
        return BerryMapper.toOutputDto(updatedBerry);
    }

    public String deleteBerry(Long id) {
        // Retrieve the Berry to be deleted
        Berry berry = berryRepository.findById(id).orElseThrow(() -> new BerryNotFoundException(id));

        // Find all BerryPlantingSites that have this Berry
        List<BerryPlantingSite> sites = berryPlantingSiteRepository.findBerryPlantingSiteByPlantedBerriesBySlotsEquals(berry);

        // Update each BerryPlantingSite to remove the berry
        for (BerryPlantingSite site : sites) {
//            site.getPlantedBerriesBySlots().remove(1);
            for (int i = 1; i <= site.getSoilSlots(); i++) {
                if (site.getPlantedBerriesBySlots().get(i) == berry) {
                    site.getPlantedBerriesBySlots().remove(i);
                }
            }
        }

        berryRepository.deleteById(id);

        return berry.getName() + " berry deleted successfully";
    }
}
