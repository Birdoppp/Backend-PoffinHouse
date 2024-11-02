package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.dto.mapper.BerryMapper;
import com.novi.poffinhouse.exceptions.BerryNotFoundException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.game.gamemap.BerryPlantingSite;
import com.novi.poffinhouse.repositories.BerryPlantingSiteRepository;
import com.novi.poffinhouse.repositories.BerryRepository;
import com.novi.poffinhouse.util.Capitalize;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
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

    public BerryOutputDto createBerry(@Valid BerryInputDto berryInputDto) {
        if (berryRepository.existsByNameIgnoreCase(berryInputDto.getName())) {
            throw new IllegalArgumentException("A Berry with the name '" + Capitalize.getCapitalizedString(berryInputDto.getName()) + "' already exists.");
        }

        Berry berry = BerryMapper.toEntity(berryInputDto);
        Berry savedBerry = berryRepository.save(berry);
        return BerryMapper.toOutputDto(savedBerry);
    }

    public BerryOutputDto getBerryById(Long id) {
        Berry berry = berryRepository.findById(id)
                .orElseThrow(() -> new BerryNotFoundException(id));
        return BerryMapper.toOutputDto(berry);
    }

    public BerryOutputDto getBerryByIndexNumber(Long indexNumber) {
        Berry berry = berryRepository.findByIndexNumber(indexNumber)
                .orElseThrow(() -> new BerryNotFoundException(indexNumber));
        return BerryMapper.toOutputDto(berry);
    }

    public List<BerryOutputDto> getAllBerries() {
        return berryRepository.findAll()
                .stream()
                .map(BerryMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public List<BerryOutputDto> getAllValidatedBerriesOrderedByIndexNumber() {
        return berryRepository.findValidatedBerriesOrderedByIndexNumber()
                .stream()
                .map(BerryMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public List<BerryOutputDto> getUnvalidatedBerries() {
        return berryRepository.findUnvalidatedBerriesOrderedByIndexNumber()
                .stream()
                .map(BerryMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public BerryOutputDto updateBerry(Long indexNumber, @Valid BerryInputDto berryInputDto) {
        Berry berry = berryRepository.findByIndexNumber(indexNumber)
                .orElseThrow(() -> new BerryNotFoundException(indexNumber));

        berry.setName(berryInputDto.getName());
        berry.setDescription(berryInputDto.getDescription());
        berry.setGrowthTime(berryInputDto.getGrowthTime());
        berry.setCategoryType(berryInputDto.getCategoryType());
        berry.setSpicyPotency(berryInputDto.getSpicyPotency());
        berry.setDryPotency(berryInputDto.getDryPotency());
        berry.setSweetPotency(berryInputDto.getSweetPotency());
        berry.setBitterPotency(berryInputDto.getBitterPotency());
        berry.setSourPotency(berryInputDto.getSourPotency());
        berry.setValidated(false);

        Berry updatedBerry = berryRepository.save(berry);
        return BerryMapper.toOutputDto(updatedBerry);
    }

    public BerryOutputDto validateBerry(Long indexNumber) {
        Berry berry = berryRepository.findByIndexNumber(indexNumber).orElseThrow(() -> new BerryNotFoundException(indexNumber));
        berry.setValidated(true);
        berryRepository.save(berry);
        return BerryMapper.toOutputDto(berry);
    }

    public String deleteBerry(Long indexNumber) {
        Berry berry = berryRepository.findByIndexNumber(indexNumber).orElseThrow(() -> new BerryNotFoundException(indexNumber));

        // Find all BerryPlantingSites that have this Berry
        List<BerryPlantingSite> sites = berryPlantingSiteRepository.findBerryPlantingSiteByPlantedBerriesBySlotsEquals(berry);

        // Delete Berry from all Games
        if (berry.getGames() != null) {
            berry.getGames().forEach(game -> game.getBerryList().remove(berry));
        }
        // Update each BerryPlantingSite to remove the berry
        for (BerryPlantingSite site : sites) {
//            site.getPlantedBerriesBySlots().remove(1);
            for (int i = 1; i <= site.getSoilSlots(); i++) {
                if (site.getPlantedBerriesBySlots().get(i) == berry) {
                    site.getPlantedBerriesBySlots().remove(i);
                }
            }
        }

        berryRepository.deleteByIndexNumber(indexNumber);

        return berry.getName() + " berry deleted successfully";
    }
}
