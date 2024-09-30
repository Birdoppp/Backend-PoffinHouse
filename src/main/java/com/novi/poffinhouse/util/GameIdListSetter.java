package com.novi.poffinhouse.util;

import com.novi.poffinhouse.dto.input.GameIdListSetterInputDto;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.BerryRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class GameIdListSetter {

    private final PokemonRepository pokemonRepository;
    private final BerryRepository berryRepository;

    public GameIdListSetter(PokemonRepository pokemonRepository, BerryRepository berryRepository) {
        this.pokemonRepository = pokemonRepository;
        this.berryRepository = berryRepository;
    }

    public List<Pokemon> PokemonListByGeneration(GameIdListSetterInputDto inputDto, int generation) {
        List<Long> idList = inputDto.getIdList();
        if (inputDto.isAutofill()) {
            int maxNationalDex = NationalDexByGeneration.getMaxIndexByGeneration(generation);
            int startNationalDex = NationalDexByGeneration.getStartIndexByGeneration(generation);
            return pokemonRepository.findAllByValidatedTrueAndNationalDexBetween(startNationalDex, maxNationalDex);
        } else if (idList == null) {
            return Collections.emptyList();
        } else {
            return idList.stream()
                    .map(pokemonRepository::findById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toList());
        }
    }

    public List<Berry> BerryList(GameIdListSetterInputDto inputDto) {
        if (inputDto.isAutofill()) {
            return berryRepository.findAll();
        }
        if (inputDto.getIdList() == null) {
            return Collections.emptyList();
        }
        return inputDto.getIdList().stream()
                .map(berryRepository::findById)
                .filter(Optional::isPresent)
                .map(Optional::get)
                .collect(Collectors.toList());
    }
}