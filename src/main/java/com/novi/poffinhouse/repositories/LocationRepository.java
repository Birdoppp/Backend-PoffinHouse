package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.game.gamemap.Location;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocationRepository extends JpaRepository<Location, Long> {

}
