package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.game.Team;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface TeamRepository extends JpaRepository<Team, Long> {
    Optional<Team> findByGameId(Long gameId);
    List<Team> findOwnedPokemonByOwnedPokemonId(Long ownedPokemonId);

}
