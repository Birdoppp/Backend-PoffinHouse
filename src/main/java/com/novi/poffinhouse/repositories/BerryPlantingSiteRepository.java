package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.berries.Berry;
import com.novi.poffinhouse.models.game.gamemap.BerryPlantingSite;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BerryPlantingSiteRepository extends JpaRepository<BerryPlantingSite, Long> {
    List<BerryPlantingSite> findBerryPlantingSiteByPlantedBerriesBySlotsEquals(Berry berry);
}
