package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.Pokemon;

public class PokemonMapper {

    public static Pokemon toEntity(PokemonInputDto inputDto) {
        Pokemon pokemon = new Pokemon();
        pokemon.setName(inputDto.getName());
        pokemon.setNationalDex(inputDto.getNationalDex());
        pokemon.setType(inputDto.getType());
        pokemon.setHealthPoints(inputDto.getHealthPoints());
        pokemon.setAttack(inputDto.getAttack());
        pokemon.setDefence(inputDto.getDefence());
        pokemon.setSpAttack(inputDto.getSpAttack());
        pokemon.setSpDefence(inputDto.getSpDefence());
        pokemon.setSpeed(inputDto.getSpeed());
        return pokemon;
    }

    public static PokemonOutputDto toOutputDto(Pokemon pokemon) {
        PokemonOutputDto outputDto = new PokemonOutputDto();
        outputDto.setId(pokemon.getId());
        outputDto.setName(pokemon.getName());
        outputDto.setNationalDex(pokemon.getNationalDex());
        outputDto.setType(pokemon.getType());
        outputDto.setHealthPoints(pokemon.getHealthPoints());
        outputDto.setAttack(pokemon.getAttack());
        outputDto.setDefence(pokemon.getDefence());
        outputDto.setSpAttack(pokemon.getSpAttack());
        outputDto.setSpDefence(pokemon.getSpDefence());
        outputDto.setSpeed(pokemon.getSpeed());
        return outputDto;
    }
}
