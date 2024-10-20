package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AdjustListDto;
import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.mapper.GameMapper;
import com.novi.poffinhouse.dto.output.game.GameOutputDto;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.game.Game;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.*;
import com.novi.poffinhouse.util.AuthUtil;
import com.novi.poffinhouse.util.GameIdListSetter;
import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import org.springframework.security.access.AccessDeniedException;
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
    private final GameIdListSetter gameIdListSetter;
    private final UserRepository userRepository;
    private final PokemonRepository pokemonRepository;
    private final BerryRepository berryRepository;

    public GameService(GameRepository gameRepository, GameMapper gameMapper, GameIdListSetter gameIdListSetter,
                       UserRepository userRepository, PokemonRepository pokemonRepository, BerryRepository berryRepository) {
        this.gameRepository = gameRepository;
        this.gameMapper = gameMapper;
        this.gameIdListSetter = gameIdListSetter;
        this.userRepository = userRepository;
        this.pokemonRepository = pokemonRepository;
        this.berryRepository = berryRepository;
    }

    public GameOutputDto createGame(GameInputDto inputDto) {
        String username = AuthUtil.getCurrentUsername();
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found."));

        Game game = gameMapper.toEntity(inputDto);
        game.setUser(user);

        Game savedGame = gameRepository.save(game);
        return gameMapper.toOutputDto(savedGame);
    }

    public GameOutputDto getGameById(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }
        return gameMapper.toOutputDto(game);
    }

    public List<GameOutputDto> getAllGamesByUsername(String username) {
        if (!AuthUtil.isAdminOrOwner(username)) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }
        List<Game> games = gameRepository.findAllByUser_Username(username);
        return games.stream()
                .map(gameMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public List<GameOutputDto> getAllGames() {
        return gameRepository.findAll()
                .stream()
                .map(gameMapper::toOutputDto)
                .collect(Collectors.toList());
    }

    public GameOutputDto adjustGame(Long id, @Valid GameInputDto inputDto) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(existingGame.getUser().getUsername())) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }

        if (inputDto.getVersionName() != null) {
            existingGame.setVersionName(inputDto.getVersionName());
        }
        if (inputDto.getGeneration() != 0) {
            existingGame.setGeneration(inputDto.getGeneration());
        }
        if (inputDto.getDescription() != null) {
            existingGame.setDescription(inputDto.getDescription());
        }

        if (inputDto.getPokemonInput() != null) {
            existingGame.setPokemonList(gameIdListSetter.PokemonListByGeneration(inputDto.getPokemonInput(), inputDto.getGeneration()));
        }

        if (inputDto.getBerryInput() != null) {
            existingGame.setBerryList(gameIdListSetter.BerryList(inputDto.getBerryInput()));
        }

        Game savedGame = gameRepository.save(existingGame);
        return gameMapper.toOutputDto(savedGame);
    }

    //Pokemon
    public GameOutputDto adjustPokemonList(Long id, @Valid AdjustListDto adjustListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }

        List<Pokemon> pokemonListToAdd = null;
        List<Pokemon> pokemonListToRemove = null;

        if (adjustListDto.getAddList() != null) {
            pokemonListToAdd = adjustListDto.getAddList().stream()
                    .map(pokemonRepository::findByNationalDex)
                    .map(optionalPokemon -> optionalPokemon.orElseThrow(() -> new IllegalArgumentException("Pokemon not found.")))
                    .toList();
        }
        if (adjustListDto.getRemoveList() != null) {
            pokemonListToRemove = adjustListDto.getRemoveList().stream()
                    .map(pokemonRepository::findByNationalDex)
                    .map(optionalPokemon -> optionalPokemon.orElseThrow(() -> new IllegalArgumentException("Pokemon not found.")))
                    .toList();
        }

        if (pokemonListToRemove != null) {
            game.getPokemonList().removeAll(pokemonListToRemove);
        }
        if (pokemonListToAdd != null) {
            game.getPokemonList().addAll(pokemonListToAdd);
        }

        Game updatedGame = gameRepository.save(game);
        return gameMapper.toOutputDto(updatedGame);
    }

    //OwnedPokemon is separate

    //Team is separate

    //Berries
    public GameOutputDto adjustBerryList(Long id, @Valid AdjustListDto adjustListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException("You do not have permission to access this resource.");
        }

        List<Berry> berryListToAdd = null;
        List<Berry> berryListToRemove = null;

        if (adjustListDto.getAddList() != null) {
            berryListToAdd = adjustListDto.getAddList().stream()
                    .map(berryRepository::findById)
                    .map(berry -> berry.orElseThrow(() -> new IllegalArgumentException("Berry not found.")))
                    .filter(berry -> !game.getBerryList().contains(berry))
                    .toList();
        }
        if (adjustListDto.getRemoveList() != null) {
            berryListToRemove = adjustListDto.getRemoveList().stream()
                    .map(berryRepository::findById)
                    .map(berry -> berry.orElseThrow(() -> new IllegalArgumentException("Berry not found.")))
                    .filter(berry -> game.getBerryList().contains(berry))
                    .toList();
        }

        if (berryListToRemove != null) {
            game.getBerryList().removeAll(berryListToRemove);
        }
        if (berryListToAdd != null) {
            game.getBerryList().addAll(berryListToAdd);
        }

        Game updatedGame = gameRepository.save(game);
        return gameMapper.toOutputDto(updatedGame);
    }

    public void deleteGame(Long id) {
        if (!gameRepository.existsById(id)) {
            throw new IllegalArgumentException("Game with id " + id + " not found.");
        }
        gameRepository.deleteById(id);
    }
}