package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.region.Map;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MapRepository extends JpaRepository<Map, Long> {

    Optional<Map> findMapByRegionName(String regionName);
}
