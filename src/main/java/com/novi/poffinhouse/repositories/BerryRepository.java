package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.berries.Berry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface BerryRepository extends JpaRepository<Berry, Long> {
    boolean existsByNameIgnoreCase(String name);

    @Query("SELECT b FROM Berry b ORDER BY b.indexNumber")
    List<Berry> findAllOrderedByIndexNumber();
}
