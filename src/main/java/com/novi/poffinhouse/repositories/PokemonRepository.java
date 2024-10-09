package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PokemonRepository extends JpaRepository <Pokemon,Long> {

    Optional<Pokemon> findByName(String pokemonName);
    Optional<Pokemon> findByNationalDex(Long nationalDexNumber);

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT p FROM Pokemon p WHERE p.validated = true ORDER BY p.nationalDex")
    List<Pokemon> findAllValidatedOrderedByNationalDex();

    List<Pokemon> findAllByValidatedTrueAndNationalDexBetween(Long startNationalDex, Long maxNationalDex);

    @Query("SELECT p FROM Pokemon p WHERE p.validated = false ORDER BY p.nationalDex")
    List<Pokemon>  findAllUnvalidatedOrderedByNationalDex();

    void deleteByNationalDex (Long nationalDex);

}
