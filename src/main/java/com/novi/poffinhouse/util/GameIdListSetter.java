package com.novi.poffinhouse.util;

import com.novi.poffinhouse.dto.input.GameIdListSetterInputDto;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.BerryRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class GameIdListSetter {

    private final PokemonRepository pokemonRepository;
    private final BerryRepository berryRepository;

    public GameIdListSetter(PokemonRepository pokemonRepository, BerryRepository berryRepository) {
        this.pokemonRepository = pokemonRepository;
        this.berryRepository = berryRepository;
    }

    public List<Pokemon> PokemonListByGeneration(GameIdListSetterInputDto inputDto, Integer generation) {
        List<Long> idList = inputDto.getIdList();
        if (inputDto.isAutofill()) {
            Long maxNationalDex = NationalDexByGeneration.getMaxIndexByGeneration(generation);
            Long startNationalDex = NationalDexByGeneration.getStartIndexByGeneration(generation);
            return pokemonRepository.findAllByValidatedTrueAndNationalDexBetween(startNationalDex, maxNationalDex);
        } else if (idList == null) {
            return Collections.emptyList();
        } else {
            Set<Long> uniqueIds = new HashSet<>();
            return idList.stream()
                    .filter(uniqueIds::add) // Skip duplicates
                    .map(id -> pokemonRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Pokemon with Id " + id + " not found.")))
                    .collect(Collectors.toList());
        }
    }


    public List<Berry> BerryList(GameIdListSetterInputDto inputDto) {
        List<Long> idList = inputDto.getIdList();
        if (inputDto.isAutofill()) {
            return berryRepository.findAll();
        } else if (idList == null) {
            return Collections.emptyList();
        } else {
            Set<Long> uniqueIds = new HashSet<>();
            return idList.stream()
                    .filter(uniqueIds::add) // Skip duplicates
                    .map(id -> berryRepository.findById(id)
                            .orElseThrow(() -> new IllegalArgumentException("Berry with ID " + id + " not found.")))
                    .collect(Collectors.toList());
        }
    }
}