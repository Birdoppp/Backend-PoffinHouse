package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.pokemon.Team;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TeamRepository extends JpaRepository<Team, int> {
}
