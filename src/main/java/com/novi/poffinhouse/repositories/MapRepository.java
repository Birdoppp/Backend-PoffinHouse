package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.region.RegionMap;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface MapRepository extends JpaRepository<RegionMap, Long> {
     RegionMap findMapByRegionName(String regionName);
}
