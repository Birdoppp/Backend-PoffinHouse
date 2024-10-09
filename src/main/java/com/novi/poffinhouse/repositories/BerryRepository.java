package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.berries.Berry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;


@Repository
public interface BerryRepository extends JpaRepository<Berry, Long> {

    Optional<Berry> findByIndexNumber(Long indexNumber);

    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT b FROM Berry b where b.validated = true ORDER BY b.indexNumber")
    List<Berry> findValidatedBerriesOrderedByIndexNumber();

    @Query("SELECT b FROM Berry b WHERE b.validated = false ORDER BY b.indexNumber")
    List<Berry> findUnvalidatedBerriesOrderedByIndexNumber();

    void deleteByIndexNumber(Long indexNumber);
}
