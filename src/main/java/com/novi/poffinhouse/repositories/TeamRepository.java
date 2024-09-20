package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TeamRepository extends JpaRepository<Team, Long> {
   List<Team> findOwnedPokemonByOwnedPokemonId(Long ownedPokemonId);

}
