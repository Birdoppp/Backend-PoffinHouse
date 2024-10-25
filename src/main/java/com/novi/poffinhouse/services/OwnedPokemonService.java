package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.OwnedPokemonContestConditionInputDto;
import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.OwnedPokemon;
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
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }

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
       OwnedPokemon ownedPokemon = ownedPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(ownedPokemon.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        return ownedPokemonMapper.toOutputDto(ownedPokemon);
    }

    public List<OwnedPokemonOutputDto> getAllOwnedPokemonByGameId(Long gameId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + gameId + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        return ownedPokemonRepository.findAllByGameId(gameId)
                .stream()
                .map(ownedPokemonMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public List<OwnedPokemonOutputDto> getAllOwnedPokemon() {
        return ownedPokemonRepository.findAll()
                .stream()
                .map(ownedPokemonMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public OwnedPokemonOutputDto adjustContestCondition(Long id, @Valid OwnedPokemonContestConditionInputDto inputDto) {
        OwnedPokemon existingOwnedPokemon = ownedPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with id " + id + " not found."));
        if(!AuthUtil.isAdminOrOwner(existingOwnedPokemon.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        if (inputDto.getBeauty() != null) {
            existingOwnedPokemon.setBeauty(inputDto.getBeauty());
        }
        if (inputDto.getCoolness() != null) {
            existingOwnedPokemon.setCoolness(inputDto.getCoolness());
        }
        if (inputDto.getCuteness() != null) {
            existingOwnedPokemon.setCuteness(inputDto.getCuteness());
        }
        if (inputDto.getCleverness() != null) {
            existingOwnedPokemon.setCleverness(inputDto.getCleverness());
        }
        if (inputDto.getToughness() != null) {
            existingOwnedPokemon.setToughness(inputDto.getToughness());
        }

        OwnedPokemon updatedOwnedPokemon = ownedPokemonRepository.save(existingOwnedPokemon);
        return ownedPokemonMapper.toOutputDto(updatedOwnedPokemon);
    }

    public String deleteOwnedPokemon(Long id) {
        OwnedPokemon ownedPokemon = ownedPokemonRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("OwnedPokemon with Id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(ownedPokemon.getGame().getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        List<Team> teams = teamRepository.findOwnedPokemonByOwnedPokemonId(ownedPokemon.getId());
        for (Team team : teams) {
            team.getOwnedPokemon().remove(ownedPokemon);
        }
        ownedPokemonRepository.deleteById(id);

        return "OwnedPokemon " + ownedPokemon.getPokemon().getName() + " with Id " + id + " deleted successfully.";

    }
}
