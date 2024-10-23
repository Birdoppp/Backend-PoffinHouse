package com.novi.poffinhouse.services;

import com.novi.poffinhouse.dto.input.AssignAuthorityToUserDto;
import com.novi.poffinhouse.dto.mapper.AuthorityMapper;
import com.novi.poffinhouse.dto.output.AuthorityOutputDto;
import com.novi.poffinhouse.exceptions.AccessDeniedException;
import com.novi.poffinhouse.exceptions.UserNotFoundException;
import com.novi.poffinhouse.models.auth.Authority;
import com.novi.poffinhouse.models.auth.User;
import com.novi.poffinhouse.repositories.UserRepository;
import com.novi.poffinhouse.util.AuthUtil;
import com.novi.poffinhouse.util.RoleEnum;
import com.novi.poffinhouse.repositories.AuthorityRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

import java.util.Set;
import java.util.stream.Collectors;

@Validated
@Transactional
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

    public AuthorityOutputDto assignAuthority(AssignAuthorityToUserDto assignAuthorityToUserDto) {
        RoleEnum role = assignAuthorityToUserDto.getRole();
        String username = assignAuthorityToUserDto.getUsername();

        if (role == null || username == null) {
            throw new IllegalArgumentException("Username and role must not be null");
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }

        Authority authority = new Authority();
        authority.setAuthority(role);
        authority.setUsername(username);
        authority.setUser(user);

        Authority savedAuthority = authorityRepository.save(authority);
        return AuthorityMapper.toOutputDto(savedAuthority);
    }

    public Set<AuthorityOutputDto> getAuthoritiesByUsername(String username) {
        User user = userRepository.findByUsername(username).orElseThrow(() -> new UserNotFoundException("User not found with username: " + username));
        if (AuthUtil.isAdminOrOwner(username)) {
            throw new AccessDeniedException();
        }
        Set<Authority> authorities = authorityRepository.findByUsername(username);

        return authorities.stream()
                .map(AuthorityMapper::toOutputDto)
                .collect(Collectors.toSet());
    }


    public String removeAllAuthorities(String username) {
        if (username == null) {
            throw new IllegalArgumentException("Username must not be null");
        }

        User user = userService.findByUsername(username);
        if (user == null) {
            throw new UserNotFoundException("User not found with username: " + username);
        }
        user.getAuthorities().clear();

        return "Authorization has been removed for " + username;
    }
}