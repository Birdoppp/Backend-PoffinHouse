package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.BerryInputDto;
import com.novi.poffinhouse.dto.output.BerryOutputDto;
import static com.novi.poffinhouse.dto.mapper.BerryMapper.*;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.repositories.BerryRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class BerryService {

    private final BerryRepository berryRepository;

    public BerryService(BerryRepository berryRepository) {
        this.berryRepository = berryRepository;
    }

    // GetAll
    public List<BerryOutputDto> getAllBerries() {
        List<Berry> berryList = berryRepository.findAll();
        return berryModelListToOutputList(berryList);
    }

    // Get ONE
    public BerryOutputDto getBerryById(Long id) {
        Optional<Berry> optionalBerry = berryRepository.findBerryById(id);
        if (optionalBerry.isPresent()){
            return berryFromModelToOutput(optionalBerry.get());
        } else {
            throw new RuntimeException("Id not found.");
        }
    }

//     Create
    public BerryOutputDto createBerry(BerryInputDto berryInputDto, int indexNumber) {
        Optional<Berry> optionalBerry = berryRepository.findBerryById(Long.valueOf((int) indexNumber));
        if (optionalBerry.isEmpty()) {
            Berry berry = berryRepository.save(berryFromInputDtoToModel(berryInputDto, indexNumber));
            return berryFromModelToOutput(berry);
        } else {
            throw new RuntimeException("IndexNumber already exists. Please use a different integer");
        }
    }
}
