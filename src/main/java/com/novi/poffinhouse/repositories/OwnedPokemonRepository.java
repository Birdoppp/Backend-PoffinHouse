package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.OwnedPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OwnedPokemonRepository extends JpaRepository<OwnedPokemon, Long> {


}