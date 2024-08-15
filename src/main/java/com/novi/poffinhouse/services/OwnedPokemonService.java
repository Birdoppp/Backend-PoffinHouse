package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.OwnedPokemonInputDto;
import com.novi.poffinhouse.dto.mapper.OwnedPokemonMapper;
import com.novi.poffinhouse.dto.output.OwnedPokemonOutputDto;
import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import com.novi.poffinhouse.models.pokemon.Pokemon;
import com.novi.poffinhouse.repositories.OwnedPokemonRepository;
import com.novi.poffinhouse.repositories.PokemonRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OwnedPokemonService {

    private final OwnedPokemonRepository ownedPokemonRepository;
    private final PokemonRepository pokemonRepository;
    private final OwnedPokemonMapper ownedPokemonMapper;

    public OwnedPokemonService(OwnedPokemonRepository ownedPokemonRepository, PokemonRepository pokemonRepository, OwnedPokemonMapper ownedPokemonMapper) {
        this.ownedPokemonRepository = ownedPokemonRepository;
        this.pokemonRepository = pokemonRepository;
        this.ownedPokemonMapper = ownedPokemonMapper;
    }

    @Transactional
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
        existingOwnedPokemon.setNature(inputDto.getNature().toString());
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

    public void deleteOwnedPokemon(Long id) {
        ownedPokemonRepository.deleteById(id);
    }
}
