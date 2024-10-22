package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.game.Game;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GameRepository extends JpaRepository<Game, Long> {
    List<Game> findAllByUser_Username(String username);
}