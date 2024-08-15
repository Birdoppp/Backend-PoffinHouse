package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.mapper.AuthorityMapper;
import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.RoleEnum;
import com.novi.poffinhouse.repositories.AuthorityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthorityService {

    private final AuthorityRepository authorityRepository;
    private final UserService userService;
    private final UserRepository userRepository;

    public AuthorityService(AuthorityRepository authorityRepository, UserService userService, UserRepository userRepository) {
        this.authorityRepository = authorityRepository;
        this.userService = userService;
        this.userRepository = userRepository;
    }

    // Assign a role to a user
    public AuthorityOutputDto assignAuthority(String username, RoleEnum role) {
        if (username == null || role == null) {
            throw new IllegalArgumentException("Username and role must not be null");
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        Authority authority = new Authority();
        authority.setAuthority(role.name());
        authority.setUsername(username);
        authority.setUser(user);

        Authority savedAuthority = authorityRepository.save(authority);
        return AuthorityMapper.toOutputDto(savedAuthority);
    }

    // Get authorities for a specific user
    public Set<AuthorityOutputDto> getAuthoritiesByUsername(String username) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isEmpty()) {
            throw new RuntimeException("User not found with username: " + username);
        }
        user.get();
        Set<Authority> authorities = authorityRepository.findByUsername(username);

        return authorities.stream()
                .map(AuthorityMapper::toOutputDto)
                .collect(Collectors.toSet());
    }

    // Remove authorities from a user
    @Transactional
    public void removeAllAuthorities(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }
        User user = userService.findByUsername(username);
        authorityRepository.deleteById(user.getId());
    }
}