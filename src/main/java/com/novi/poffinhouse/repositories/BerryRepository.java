package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.berries.Berry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface BerryRepository extends JpaRepository<Berry, Long> {
}
