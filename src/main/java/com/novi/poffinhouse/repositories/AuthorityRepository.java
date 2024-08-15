package com.novi.poffinhouse.repositories;

import com.novi.poffinhouse.models.auth.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface AuthorityRepository extends JpaRepository<Authority, Long> {
    Set<Authority> findByUsername(String username);
    void deleteByUsername(String username);
}
