package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.PokemonInputDto;
import com.novi.poffinhouse.dto.output.PokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import org.springframework.stereotype.Component;

@Component
public class PokemonMapper {

    public Pokemon toEntity(PokemonInputDto inputDto) {
        if (inputDto == null) {
            return null;
        }

        Pokemon pokemon = new Pokemon();
        pokemon.setName(inputDto.getName());
        pokemon.setNationalDex(inputDto.getNationalDex());
        pokemon.setType(inputDto.getType());

        return pokemon;
    }

    public PokemonOutputDto toOutputDto(Pokemon pokemon) {
        if (pokemon == null) {
            return null;
        }

        PokemonOutputDto outputDto = new PokemonOutputDto();
        outputDto.setId(pokemon.getId());
        outputDto.setName(pokemon.getName());
        outputDto.setNationalDex(pokemon.getNationalDex());
        outputDto.setType(pokemon.getType());

        return outputDto;
    }
}
