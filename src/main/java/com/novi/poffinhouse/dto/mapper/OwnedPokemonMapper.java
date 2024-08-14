package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import org.springframework.stereotype.Component;

@Component
public class OwnedPokemonMapper {

    public OwnedPokemon toModel(OwnedPokemonInputDto inputDto) {
        OwnedPokemon ownedPokemon = new OwnedPokemon();
        ownedPokemon.setNickname(inputDto.getNickname());
        ownedPokemon.setNature(inputDto.getNature());
        ownedPokemon.setCaughtByTrainerName(inputDto.getCaughtByTrainerName());
        ownedPokemon.setBeauty(inputDto.getBeauty());
        ownedPokemon.setCoolness(inputDto.getCoolness());
        ownedPokemon.setCuteness(inputDto.getCuteness());
        ownedPokemon.setCleverness(inputDto.getCleverness());
        ownedPokemon.setToughness(inputDto.getToughness());
        return ownedPokemon;
    }

    public OwnedPokemonOutputDto toOutputDto(OwnedPokemon ownedPokemon) {
        OwnedPokemonOutputDto outputDto = new OwnedPokemonOutputDto();
        outputDto.setId(ownedPokemon.getId());
        outputDto.setNickname(ownedPokemon.getNickname());
        outputDto.setNature(ownedPokemon.getNature());
        outputDto.setCaughtByTrainerName(ownedPokemon.getCaughtByTrainerName());
        outputDto.setBeauty(ownedPokemon.getBeauty());
        outputDto.setCoolness(ownedPokemon.getCoolness());
        outputDto.setCuteness(ownedPokemon.getCuteness());
        outputDto.setCleverness(ownedPokemon.getCleverness());
        outputDto.setToughness(ownedPokemon.getToughness());
        outputDto.setPokemonName(ownedPokemon.getPokemon().getName());
        return outputDto;
    }
}
