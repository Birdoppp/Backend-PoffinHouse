package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;

public class OwnedPokemonMapper {
    public static OwnedPokemon toModel(OwnedPokemonInputDto inputDto) {
        OwnedPokemon ownedPokemon = new OwnedPokemon();
//        ownedPokemon.setPokemonName(inputDto.getPokemonName());
        ownedPokemon.setNickname(inputDto.getNickname());
        ownedPokemon.setNature(inputDto.getNature());
        ownedPokemon.setCaughtByTrainerName(inputDto.getCaughtByTrainerName());

        return ownedPokemon;
    }

    public static OwnedPokemonOutputDto toOutputDto(OwnedPokemon ownedPokemon) {
        OwnedPokemonOutputDto outputDto = new OwnedPokemonOutputDto();
        outputDto.setId(ownedPokemon.getId());
        outputDto.setPokemonName(ownedPokemon.getPokemon().getName());
        outputDto.setNickname(ownedPokemon.getNickname());
        outputDto.setNature(ownedPokemon.getNature());
        outputDto.setCaughtByTrainerName(ownedPokemon.getCaughtByTrainerName());

        return outputDto;
    }
}
