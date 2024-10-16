package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.game.OwnedPokemon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OwnedPokemonRepository extends JpaRepository<OwnedPokemon, Long> {

    @Query("SELECT op FROM OwnedPokemon op WHERE op.game.id = :gameId")
    List<OwnedPokemon> findAllByGameId(Long gameId);

}