package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Pokemon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PokemonRepository extends JpaRepository <Pokemon,Long> {
}
