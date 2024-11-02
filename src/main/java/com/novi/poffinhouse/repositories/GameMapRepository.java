package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.game.gamemap.GameMap;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface GameMapRepository extends JpaRepository<GameMap, Long> {
    List<GameMap> findByGame_UserUsername(String username);
}