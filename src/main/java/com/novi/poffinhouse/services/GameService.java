package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AdjustListDto;
import com.novi.poffinhouse.dto.input.GameAdjustInputDto;
import com.novi.poffinhouse.dto.input.GameInputDto;
import com.novi.poffinhouse.dto.mapper.GameMapper;
import com.novi.poffinhouse.dto.output.game.GameOutputDto;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
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
            throw new AccessDeniedException();
        }
        return gameMapper.toOutputDto(game);
    }

    public List<GameOutputDto> getAllGamesByUsername(String username) {
        if (!AuthUtil.isAdminOrOwner(username)) {
            throw new AccessDeniedException();
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

    public GameOutputDto adjustGame(Long id, @Valid GameAdjustInputDto adjustInputDto) {
        Game existingGame = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(existingGame.getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        if (adjustInputDto.getVersionName() != null) {
            existingGame.setVersionName(adjustInputDto.getVersionName());
        }
        if (adjustInputDto.getGeneration() != 0) {
            existingGame.setGeneration(adjustInputDto.getGeneration());
        }
        if (adjustInputDto.getDescription() != null) {
            existingGame.setDescription(adjustInputDto.getDescription());
        }

        if (adjustInputDto.getPokemonInput() != null) {
            existingGame.setPokemonList(gameIdListSetter.PokemonListByGeneration(adjustInputDto.getPokemonInput(), adjustInputDto.getGeneration()));
        }

        if (adjustInputDto.getBerryInput() != null) {
            existingGame.setBerryList(gameIdListSetter.BerryList(adjustInputDto.getBerryInput()));
        }

        Game savedGame = gameRepository.save(existingGame);
        return gameMapper.toOutputDto(savedGame);
    }

    //Pokemon
    public GameOutputDto adjustPokemonList(Long id, @Valid AdjustListDto adjustListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        if (adjustListDto.getAddList() != null) {
            List<Pokemon> pokemonToAdd = adjustListDto.getAddList().stream()
                    .map(indexNumber -> pokemonRepository.findByNationalDex(indexNumber)
                            .orElseThrow(() -> new IllegalArgumentException("Pokemon with index number " + indexNumber + " not found.")))
                    .filter(pokemon -> !game.getPokemonList().contains(pokemon))
                    .toList();
            game.getPokemonList().addAll(pokemonToAdd);
        }

        if (adjustListDto.getRemoveList() != null) {
            List<Pokemon> pokemonToRemove = adjustListDto.getRemoveList().stream()
                    .map(indexNumber -> pokemonRepository.findByNationalDex(indexNumber)
                            .orElseThrow(() -> new IllegalArgumentException("Pokemon with index number " + indexNumber + " not found.")))
                    .filter(pokemon -> game.getPokemonList().contains(pokemon))
                    .toList();
            game.getPokemonList().removeAll(pokemonToRemove);
        }

        Game savedGame = gameRepository.save(game);
        return gameMapper.toOutputDto(savedGame);
    }

    //OwnedPokemon is separate

    //Team is separate

    //Berries
    public GameOutputDto adjustBerryList(Long id, @Valid AdjustListDto adjustListDto) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }

        if (adjustListDto.getAddList() != null) {
            List<Berry> berriesToAdd = adjustListDto.getAddList().stream()
                    .map(indexNumber -> berryRepository.findByIndexNumber(indexNumber)
                            .orElseThrow(() -> new IllegalArgumentException("Berry with index number " + indexNumber + " not found.")))
                    .filter(berry -> game.getBerryList().contains(berry))
                    .toList();
            game.getBerryList().addAll(berriesToAdd);
        }

        if (adjustListDto.getRemoveList() != null) {
            List<Berry> berriesToRemove = adjustListDto.getRemoveList().stream()
                    .map(indexNumber -> berryRepository.findByIndexNumber(indexNumber)
                            .orElseThrow(() -> new IllegalArgumentException("Berry with index number " + indexNumber + " not found.")))
                    .filter(berry -> game.getBerryList().contains(berry))
                    .toList();
            game.getBerryList().removeAll(berriesToRemove);
        }

        Game savedGame = gameRepository.save(game);
        return gameMapper.toOutputDto(savedGame);
    }

    public void deleteGame(Long id) {
        Game game = gameRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Game with id " + id + " not found."));
        if (!AuthUtil.isAdminOrOwner(game.getUser().getUsername())) {
            throw new AccessDeniedException();
        }
        gameRepository.deleteById(id);
    }
}