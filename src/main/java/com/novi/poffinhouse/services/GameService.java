package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AdjustIdListDto;
import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.GameMapper;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.dto.output.game.GameOutputDto;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.game.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.game.Team;
import com.novi.poffinhouse.repositories.*;
import com.novi.poffinhouse.util.AuthUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.List;
import java.util.stream.Collectors;

@Validated
@Transactional
@Service
public class GameService {

    private final GameRepository gameRepository;
    private final GameMapper gameMapper;
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonRepository ownedPokemonRepository;
    private final BerryRepository berryRepository;
    private final TeamRepository teamRepository;
    private final OwnedPokemonMapper ownedPokemonMapper;

    public GameService(GameRepository gameRepository, GameMapper gameMapper, UserRepository userRepository, PokemonRepository pokemonRepository,
                       OwnedPokemonRepository ownedPokemonRepository, BerryRepository berryRepository,
                       TeamRepository teamRepository, OwnedPokemonMapper ownedPokemonMapper) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.berryRepository = berryRepository;
        this.teamRepository = teamRepository;
        this.ownedPokemonMapper = ownedPokemonMapper;
    }

    public GameOutputDto createGame(GameInputDto inputDto) {
        String username = AuthUtil.getCurrentUsername();

        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Game game = gameMapper.toEntity(inputDto);
        game.setUser(user);

        Game savedGame = gameRepository.save(game);
        return gameMapper.toDto(savedGame);
    }

    public GameOutputDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        return gameMapper.toDto(game);
    }

    public List<GameOutputDto> getAllGamesByUsername(String username) {
        List<Game> games = gameRepository.findAllByUser_Username(username);
        return games.stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    public List<GameOutputDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toDto)
                .collect(Collectors.toList());
    }

    public GameOutputDto updateGame(Long id, GameInputDto inputDto) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));

        existingGame.setVersionName(inputDto.getVersionName());
//        existingGame.setGeneration(inputDto.getGeneration());
        existingGame.setDescription(inputDto.getDescription());
        existingGame.setRegionMap(gameMapper.toEntity(inputDto).getRegionMap());

        Game updatedGame = gameRepository.save(existingGame);
        return gameMapper.toDto(updatedGame);
    }

    //Pokemon
//    public void setPokemonListForGame(Long gameId, GameSetIdListInputDto inputDto) {
//        Game game = gameRepository.findById(gameId).orElseThrow(() -> new RuntimeException("Game not found"));
//        List<Pokemon> pokemonList = pokemonRepository.findAllByValidatedTrueAndNationalDexBetween(NationalDexByGeneration.getNationalDexByGeneration(game.getGeneration()));
//        GameSetIdList.setPokemonList(pokemonList);
//        gameRepository.save(game);
//    }

    public GameOutputDto patchPokemonList(Long id, AdjustIdListDto adjustIdListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " not found."));
//        List<Pokemon> existingPokemonList = game.getPokemonList();
//      Add Method to skip double pokemon and check if pokemon is validated
        List<Pokemon> pokemonListToAdd = adjustIdListDto.getAddIdList().stream()
                .map(pokemonRepository::findById)
                .map(pokemon -> pokemon.orElseThrow(() -> new IllegalArgumentException("Pokemon not found.")))
                .toList();

        List<Pokemon> pokemonListToRemove = adjustIdListDto.getRemoveIdList().stream()
                .map(pokemonRepository::findById)
                .map(pokemon -> pokemon.orElseThrow(() -> new IllegalArgumentException("Pokemon not found.")))
                .toList();

        game.getPokemonList().removeAll(pokemonListToRemove);
        game.getPokemonList().addAll(pokemonListToAdd);

        Game updatedGame = gameRepository.save(game);
        return gameMapper.toDto(updatedGame);
    }

    //OwnedPokemon
    public OwnedPokemonOutputDto createOwnedPokemon(Long id, @Valid OwnedPokemonInputDto ownedPokemonInputDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));

        OwnedPokemon ownedPokemon = ownedPokemonMapper.toEntity(ownedPokemonInputDto);
        ownedPokemon.setPokemon(pokemonRepository.findByName(ownedPokemonInputDto.getPokemonName())
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with name " + ownedPokemonInputDto.getPokemonName() + " not found.")));

        ownedPokemon.setGame(game);
        ownedPokemon.setUsername(AuthUtil.getCurrentUsername());

        OwnedPokemon savedOwnedPokemon = ownedPokemonRepository.save(ownedPokemon);
        return ownedPokemonMapper.toOutputDto(savedOwnedPokemon);
    }


    //Team
    public GameOutputDto updateTeam(Long gameId, Long teamId) {
        Game game = gameRepository.findById(gameId)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + gameId + " not found."));

        Team team = teamRepository.findById(teamId)
                .orElseThrow(() -> new IllegalArgumentException("Team with id " + teamId + " not found."));

        game.setTeam(team);
        Game updatedGame = gameRepository.save(game);
        return gameMapper.toDto(updatedGame);
    }

    //Berries
    public GameOutputDto patchBerryList(Long id, AdjustIdListDto adjustIdListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));

        List<Berry> berryListToAdd = adjustIdListDto.getAddIdList().stream()
                .map(berryRepository::findById)
                .map(berry -> berry.orElseThrow(() -> new IllegalArgumentException("Berry not found.")))
                .toList();

        List<Berry> berryListToRemove = adjustIdListDto.getRemoveIdList().stream()
                .map(berryRepository::findById)
                .map(berry -> berry.orElseThrow(() -> new IllegalArgumentException("Berry not found.")))
                .toList();

        game.getBerryList().removeAll(berryListToRemove);
        game.getBerryList().addAll(berryListToAdd);

        Game updatedGame = gameRepository.save(game);
        return gameMapper.toDto(updatedGame);
    }


    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Game with id " + id + " not found.");
        }
        gameRepository.deleteById(id);
    }


}
