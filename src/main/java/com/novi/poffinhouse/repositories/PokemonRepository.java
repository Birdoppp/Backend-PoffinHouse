package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PokemonRepository extends JpaRepository <Pokemon,Long> {
    Optional<Pokemon> findByName(String pokemonName);
}
