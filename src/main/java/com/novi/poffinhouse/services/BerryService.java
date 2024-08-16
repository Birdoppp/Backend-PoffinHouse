package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import com.novi.poffinhouse.dto.mapper.BerryMapper;
import com.novi.poffinhouse.exceptions.BerryNotFoundException;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.repositories.BerryRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BerryService {

    private final BerryRepository berryRepository;

    public BerryService(BerryRepository berryRepository) {
        this.berryRepository = berryRepository;
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

    @Transactional
    public String deleteBerry(Long id) {
        if (!berryRepository.existsById(id)) {
            throw new BerryNotFoundException(id);
        }
        Berry berry = berryRepository.findById(id).get();
        berryRepository.deleteById(berry.getId());
        return berry.getName() + " berry deleted successfully";
    }
}
