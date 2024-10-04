package com.novi.poffinhouse.dto.mapper;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import com.novi.poffinhouse.repositories.GameRepository;
import org.springframework.stereotype.Component;

@Component
public class OwnedPokemonMapper {
    private final GameRepository gameRepository;

    public OwnedPokemonMapper(GameRepository gameRepository) {
        this.gameRepository = gameRepository;
    }

    public OwnedPokemon toEntity(OwnedPokemonInputDto inputDto) {
        OwnedPokemon ownedPokemon = new OwnedPokemon();
        Game game = gameRepository.findById(inputDto.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("Game not found."));
        ownedPokemon.setGame(game);
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
        outputDto.setGameId(ownedPokemon.getGame().getId());
        outputDto.setId(ownedPokemon.getId());
        outputDto.setUsername(ownedPokemon.getGame().getUser().getUsername());
        outputDto.setNickname(ownedPokemon.getNickname());
        outputDto.setNature(ownedPokemon.getNature().toString());
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
