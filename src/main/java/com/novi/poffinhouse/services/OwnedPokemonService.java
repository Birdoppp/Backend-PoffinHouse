package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.game.Team;
import com.novi.poffinhouse.repositories.GameRepository;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.repositories.TeamRepository;
import com.novi.poffinhouse.util.AuthUtil;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class OwnedPokemonService {

    private final GameRepository gameRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonMapper ownedPokemonMapper;
    private final TeamRepository teamRepository;

    public OwnedPokemonService(GameRepository gameRepository, OwnedPokemonRepository ownedPokemonRepository, PokemonRepository pokemonRepository, OwnedPokemonMapper ownedPokemonMapper, TeamRepository teamRepository) {
        this.gameRepository = gameRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonMapper = ownedPokemonMapper;
        this.teamRepository = teamRepository;
    }

    public OwnedPokemonOutputDto createOwnedPokemon(@Valid OwnedPokemonInputDto inputDto) {
        Game game = gameRepository.findById(inputDto.getGameId())
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + inputDto.getGameId() + " not found."));

        // Check if the pokemonName exists in the pokemonList of the Game
        boolean pokemonExistsInGame = game.getPokemonList().stream()
                .anyMatch(pokemon -> pokemon.getName().equals(inputDto.getPokemonName()));

        if (!pokemonExistsInGame) {
            throw new IllegalArgumentException("Pokemon with name " + inputDto.getPokemonName() + " does not exist in the game's pokemon list.");
        }

        OwnedPokemon ownedPokemon = ownedPokemonMapper.toEntity(inputDto);
        ownedPokemon.setPokemon(pokemonRepository.findByName(inputDto.getPokemonName())
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with name " + inputDto.getPokemonName() + " not found.")));

        ownedPokemon.setGame(game);
        ownedPokemon.setUsername(AuthUtil.getCurrentUsername());

        OwnedPokemon savedOwnedPokemon = ownedPokemonRepository.save(ownedPokemon);
        return ownedPokemonMapper.toOutputDto(savedOwnedPokemon);
    }


    public OwnedPokemonOutputDto getOwnedPokemonById(Long id) {
        Optional<OwnedPokemon> optionalOwnedPokemon = ownedPokemonRepository.findById(id);
        if (optionalOwnedPokemon.isPresent()) {
            return ownedPokemonMapper.toOutputDto(optionalOwnedPokemon.get());
        }
        throw new IllegalArgumentException("OwnedPokemon with id " + id + " not found.");
    }

    public List<OwnedPokemonOutputDto> getAllOwnedPokemon() {
        return ownedPokemonRepository.findAll()
                .stream()
                .map(ownedPokemonMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public OwnedPokemonOutputDto updateOwnedPokemon(Long id, OwnedPokemonInputDto inputDto) {
        OwnedPokemon existingOwnedPokemon = ownedPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with id " + id + " not found."));

        existingOwnedPokemon.setNickname(inputDto.getNickname());
        existingOwnedPokemon.setNature(inputDto.getNature());
        existingOwnedPokemon.setCaughtByTrainerName(inputDto.getCaughtByTrainerName());
        existingOwnedPokemon.setBeauty(inputDto.getBeauty());
        existingOwnedPokemon.setCoolness(inputDto.getCoolness());
        existingOwnedPokemon.setCuteness(inputDto.getCuteness());
        existingOwnedPokemon.setCleverness(inputDto.getCleverness());
        existingOwnedPokemon.setToughness(inputDto.getToughness());

        Pokemon pokemon = pokemonRepository.findByName(inputDto.getPokemonName())
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with name " + inputDto.getPokemonName() + " not found."));
        existingOwnedPokemon.setPokemon(pokemon);

        OwnedPokemon updatedOwnedPokemon = ownedPokemonRepository.save(existingOwnedPokemon);
        return ownedPokemonMapper.toOutputDto(updatedOwnedPokemon);
    }

    public String deleteOwnedPokemon(Long id) {
        OwnedPokemon ownedPokemon = ownedPokemonRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with Id " + id + " not found."));
        List<Team> teams = teamRepository.findOwnedPokemonByOwnedPokemonId(ownedPokemon.getId());
        for (Team team : teams) {
            team.getOwnedPokemon().remove(ownedPokemon);
        }
        ownedPokemonRepository.deleteById(id);

        return "OwnedPokemon with Id " + id + " deleted successfully.  Note: This OwnedPokemon was removed from team.";

    }
}
