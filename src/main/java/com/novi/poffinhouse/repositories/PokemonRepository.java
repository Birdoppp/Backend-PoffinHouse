package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository <Pokemon,Long> {
    boolean existsByNameIgnoreCase(String name);

    Optional<Pokemon> findByName(String pokemonName);
    Optional<Pokemon> findByNationalDex(int nationalDexNumber);

    @Query("SELECT p FROM Pokemon p ORDER BY p.nationalDex")
    List<Pokemon> findAllOrderedByNationalDex();

    @Query("SELECT p FROM Pokemon p WHERE p.validated = true ORDER BY p.nationalDex")
    List<Pokemon> findAllValidatedOrderedByNationalDex();

    void deleteByNationalDex (int nationalDex);

}
