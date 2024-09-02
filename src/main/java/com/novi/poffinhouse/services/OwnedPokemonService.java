package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.models.pokemon.Team;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import com.novi.poffinhouse.repositories.TeamRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Transactional
@Service
public class OwnedPokemonService {

    private final OwnedPokemonRepository ownedPokemonRepository;
    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonMapper ownedPokemonMapper;
    private final TeamRepository teamRepository;

    public OwnedPokemonService(OwnedPokemonRepository ownedPokemonRepository, PokemonRepository pokemonRepository, OwnedPokemonMapper ownedPokemonMapper, TeamRepository teamRepository) {
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonMapper = ownedPokemonMapper;
        this.teamRepository = teamRepository;
    }


    public OwnedPokemonOutputDto createOwnedPokemon(OwnedPokemonInputDto inputDto) {
        Pokemon pokemon = pokemonRepository.findByName(inputDto.getPokemonName())
                .orElseThrow(() -> new IllegalArgumentException("Pokemon with name " + inputDto.getPokemonName() + " not found."));

        OwnedPokemon ownedPokemon = ownedPokemonMapper.toModel(inputDto);
        ownedPokemon.setPokemon(pokemon);

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

        return "OwnedPokemon with Id " + id + " deleted successfully.  Note: This OwnedPokemon was removed from " + teams.size() + " team(s).";

    }
}
