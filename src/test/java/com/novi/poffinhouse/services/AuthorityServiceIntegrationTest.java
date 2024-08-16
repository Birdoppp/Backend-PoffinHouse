package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.AuthorityRepository;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.RoleEnum;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class AuthorityServiceIntegrationTest {

    @Autowired
    private AuthorityService authorityService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AuthorityRepository authorityRepository;

    @Test
    void testAssignAndRetrieveAuthority() {
        User user = new User();
        user.setUsername("testuser");
        user.setEmail("testuser@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        AuthorityOutputDto authorityDto = authorityService.assignAuthority("testuser", RoleEnum.TRAINER);

        Set<Authority> authorities = authorityRepository.findByUsername("testuser");
        assertEquals(1, authorities.size());
        assertTrue(authorities.stream().anyMatch(auth -> auth.getAuthority().equals("TRAINER")));
    }

    @Test
    void testRemoveAllAuthorities() {
        User user = new User();
        user.setUsername("testuser2");
        user.setEmail("testuser2@example.com");
        user.setPassword("password123");
        userRepository.save(user);

        authorityService.assignAuthority("testuser2", RoleEnum.TRAINER);
        authorityService.removeAllAuthorities("testuser2");

        Set<Authority> authorities = authorityRepository.findByUsername("testuser2");
        assertTrue(authorities.isEmpty());
    }
}
